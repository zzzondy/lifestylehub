package com.auth.domain.repository

import com.auth.domain.models.User
import com.auth.domain.results.CreatingUserResult
import com.auth.domain.results.LoginResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val userFlow: Flow<User?>

    suspend fun createUser(password: String): CreatingUserResult

    suspend fun login(login: String, password: String): LoginResult

    suspend fun signOut()
}