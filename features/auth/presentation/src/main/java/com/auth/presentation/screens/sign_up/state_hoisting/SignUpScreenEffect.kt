package com.auth.presentation.screens.sign_up.state_hoisting

sealed class SignUpScreenEffect {

    data object ShowLoadingDialog : SignUpScreenEffect()

    data object HideLoadingDialog : SignUpScreenEffect()

    data object NavigateBack : SignUpScreenEffect()
}