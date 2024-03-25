package com.auth.presentation.screens.profile.state_hoisting

sealed class ProfileScreenEffect {

    data object NavigateToLoginScreen : ProfileScreenEffect()

    data object NavigateToSignUpScreen : ProfileScreenEffect()
}