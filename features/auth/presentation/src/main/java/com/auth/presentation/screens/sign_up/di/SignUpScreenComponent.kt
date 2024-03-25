package com.auth.presentation.screens.sign_up.di

import com.auth.presentation.screens.sign_up.SignUpScreenViewModel
import dagger.Subcomponent

@SignUpScreenComponentScope
@Subcomponent(
    modules = [SignUpScreenModule::class]
)
interface SignUpScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): SignUpScreenComponent
    }

    val signUpScreenViewModel: SignUpScreenViewModel
}