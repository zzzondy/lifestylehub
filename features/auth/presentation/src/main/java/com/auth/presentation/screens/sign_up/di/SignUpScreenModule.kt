package com.auth.presentation.screens.sign_up.di

import com.auth.domain.use_cases.CreateUserUseCase
import com.auth.domain.use_cases.ValidatePasswordUseCase
import com.auth.presentation.screens.sign_up.SignUpScreenViewModel
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCaseModule::class]
)
class SignUpScreenModule {

    @SignUpScreenComponentScope
    @Provides
    fun provideSignUpScreenViewModel(
        createUserUseCase: CreateUserUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase
    ): SignUpScreenViewModel =
        SignUpScreenViewModel(createUserUseCase, validatePasswordUseCase)
}