package com.auth.domain.use_cases

import com.auth.domain.repository.AuthRepository

class GetCurrentUserUseCase(
    private val authRepository: AuthRepository,
) {

    operator fun invoke() = authRepository.userFlow
}