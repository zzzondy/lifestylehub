package com.auth.presentation.screens.profile.di

import com.auth.domain.repository.AuthRepository
import com.auth.domain.use_cases.GetCurrentUserUseCase
import com.auth.domain.use_cases.SignOutUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @ProfileScreenComponentScope
    @Provides
    fun provideGetUserUseCase(authRepository: AuthRepository): GetCurrentUserUseCase =
        GetCurrentUserUseCase(authRepository)

    @ProfileScreenComponentScope
    @Provides
    fun provideSignOutUseCase(authRepository: AuthRepository): SignOutUseCase =
        SignOutUseCase(authRepository)
}