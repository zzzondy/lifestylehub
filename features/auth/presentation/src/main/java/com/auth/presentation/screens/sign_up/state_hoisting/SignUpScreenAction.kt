package com.auth.presentation.screens.sign_up.state_hoisting

sealed class SignUpScreenAction {

    data class OnUpdatePassword(val newPassword: String) : SignUpScreenAction()

    data object OnSignUpButtonClicked : SignUpScreenAction()

    data object OnBackButtonClicked : SignUpScreenAction()
}