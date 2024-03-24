package com.auth.domain.use_cases

import com.auth.domain.repository.AuthRepository

class SignOutUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() = authRepository.signOut()
}