package com.auth.presentation.screens.login.di

import com.auth.domain.repository.AuthRepository
import com.auth.domain.use_cases.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @LoginScreenComponentScope
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)
}