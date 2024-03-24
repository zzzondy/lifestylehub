package com.auth.presentation.screens.login.state_hoisting

sealed class LoginScreenAction {

    data class OnLoginUpdate(val newLogin: String) : LoginScreenAction()

    data class OnPasswordUpdate(val newPassword: String) : LoginScreenAction()

    data object OnLoginButtonClicked : LoginScreenAction()

    data object OnBackButtonClicked : LoginScreenAction()
}