package com.auth.data.repository

import com.auth.data.authorization.AuthDatabaseManager
import com.auth.data.local.LocalAuthRepository
import com.auth.data.remote.repository.RemoteAuthRepository
import com.auth.data.remote.results.RemoteObtainingRandomUserResult
import com.auth.data.utils.toDomain
import com.auth.data.utils.toEntity
import com.auth.domain.models.User
import com.auth.domain.repository.AuthRepository
import com.auth.domain.results.CreatingUserResult
import com.auth.domain.results.LoginResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
    private val localAuthRepository: LocalAuthRepository,
    private val remoteAuthRepository: RemoteAuthRepository,
    private val authDatabaseManager: AuthDatabaseManager,
) : AuthRepository {

    private val _user = MutableStateFlow<User?>(null)

    override val userFlow = _user.asStateFlow()

    private val authScope = CoroutineScope(Job())

    init {
        authScope.launch(Dispatchers.IO) {
            val currentUserId = localAuthRepository.getCurrentUserId()
            if (currentUserId != null) {
                _user.update {
                    authDatabaseManager.authDatabase.authDatabaseDao.getUserById(currentUserId)
                        .toDomain()
                }
            } else {
                _user.update { null }
            }
        }
    }


    override suspend fun createUser(password: String): CreatingUserResult {
        return when (val obtainingRandomUserResult = remoteAuthRepository.getRandomUser()) {
            is RemoteObtainingRandomUserResult.Success -> {
                try {
                    val createdUserId =
                        authDatabaseManager.authDatabase.authDatabaseDao.createUser(
                            obtainingRandomUserResult.randomUser.toEntity(password)
                        )

                    localAuthRepository.setCurrentUserId(createdUserId)
                    _user.update {
                        authDatabaseManager.authDatabase.authDatabaseDao.getUserById(createdUserId)
                            .toDomain()
                    }
                    CreatingUserResult.Success
                } catch (e: Exception) {
                    CreatingUserResult.Error
                }
            }

            is RemoteObtainingRandomUserResult.Error -> {
                CreatingUserResult.NetworkError
            }
        }
    }

    override suspend fun login(login: String, password: String): LoginResult {
        return try {
            val user =
                authDatabaseManager.authDatabase.authDatabaseDao.getUserByLogin(login)
            if (user == null) {
                LoginResult.WrongLoginOrPassword
            } else {
                if (user.password != password) {
                    LoginResult.WrongLoginOrPassword
                } else {
                    localAuthRepository.setCurrentUserId(user.id)
                    _user.update {
                        user.toDomain()
                    }
                    LoginResult.Success
                }
            }
        } catch (e: Exception) {
            LoginResult.Error
        }
    }

    override suspend fun signOut() {
        localAuthRepository.setCurrentUserId(-1)
        _user.update {
            null
        }
    }
}