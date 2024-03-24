package com.auth.presentation.screens.login.di

import com.auth.domain.use_cases.LoginUseCase
import com.auth.presentation.screens.login.LoginScreenViewModel
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCaseModule::class]
)
class LoginScreenModule {

    @LoginScreenComponentScope
    @Provides
    fun provideLoginScreenViewModel(loginUseCase: LoginUseCase): LoginScreenViewModel =
        LoginScreenViewModel(loginUseCase)
}