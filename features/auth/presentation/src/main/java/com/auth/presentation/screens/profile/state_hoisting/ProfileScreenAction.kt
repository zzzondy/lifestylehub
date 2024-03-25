package com.auth.presentation.screens.profile.state_hoisting

sealed class ProfileScreenAction {

    data object OnLoginButtonClicked : ProfileScreenAction()

    data object OnSignUpButtonClicked : ProfileScreenAction()

    data object OnSignOutButtonClicked : ProfileScreenAction()
}