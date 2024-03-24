package com.auth.presentation.screens.sign_up.di

import com.auth.domain.repository.AuthRepository
import com.auth.domain.use_cases.CreateUserUseCase
import com.auth.domain.use_cases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @SignUpScreenComponentScope
    @Provides
    fun provideCreateUserUseCase(authRepository: AuthRepository): CreateUserUseCase =
        CreateUserUseCase(authRepository)

    @SignUpScreenComponentScope
    @Provides
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase =
        ValidatePasswordUseCase()
}