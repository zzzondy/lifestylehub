package com.auth.presentation.screens.login.di

import com.auth.presentation.screens.login.LoginScreenViewModel
import dagger.Subcomponent

@LoginScreenComponentScope
@Subcomponent(
    modules = [LoginScreenModule::class]
)
interface LoginScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): LoginScreenComponent
    }

    val loginScreenViewModel: LoginScreenViewModel
}