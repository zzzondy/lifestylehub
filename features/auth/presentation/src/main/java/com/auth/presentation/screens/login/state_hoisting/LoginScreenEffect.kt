package com.auth.presentation.screens.login.state_hoisting

sealed class LoginScreenEffect {

    data object ShowLoadingDialog : LoginScreenEffect()

    data object HideLoadingDialog : LoginScreenEffect()

    data object NavigateBack : LoginScreenEffect()
}