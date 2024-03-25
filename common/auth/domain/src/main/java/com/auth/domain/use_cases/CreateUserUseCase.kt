package com.auth.domain.use_cases

import com.auth.domain.repository.AuthRepository

class CreateUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(password: String) = authRepository.createUser(password)
}