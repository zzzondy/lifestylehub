package com.auth.presentation.screens.profile.di

import com.auth.domain.use_cases.GetCurrentUserUseCase
import com.auth.domain.use_cases.SignOutUseCase
import com.auth.presentation.screens.profile.ProfileScreenViewModel
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCaseModule::class]
)
class ProfileScreenModule {

    @ProfileScreenComponentScope
    @Provides
    fun provideProfileScreenViewModel(
        getCurrentUserUseCase: GetCurrentUserUseCase,
        signOutUseCase: SignOutUseCase
    ): ProfileScreenViewModel =
        ProfileScreenViewModel(getCurrentUserUseCase, signOutUseCase)
}