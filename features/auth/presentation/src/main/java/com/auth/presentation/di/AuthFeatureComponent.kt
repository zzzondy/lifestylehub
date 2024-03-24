package com.auth.presentation.di

import com.auth.presentation.screens.login.di.LoginScreenComponent
import com.auth.presentation.screens.profile.di.ProfileScreenComponent
import com.auth.presentation.screens.sign_up.di.SignUpScreenComponent
import dagger.Subcomponent

@AuthFeatureComponentScope
@Subcomponent
interface AuthFeatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): AuthFeatureComponent
    }

    val profileScreenComponentFactory: ProfileScreenComponent.Factory

    val signUpScreenComponentFactory: SignUpScreenComponent.Factory

    val loginScreenComponentFactory: LoginScreenComponent.Factory
}