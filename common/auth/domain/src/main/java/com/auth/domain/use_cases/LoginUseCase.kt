package com.auth.domain.use_cases

import com.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(login: String, password: String) =
        authRepository.login(login, password)
}