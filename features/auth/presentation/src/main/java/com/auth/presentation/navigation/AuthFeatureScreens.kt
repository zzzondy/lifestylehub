package com.auth.presentation.navigation

sealed class AuthFeatureScreens(val route: String) {

    data object ProfileScreen : AuthFeatureScreens(route = "profile_screen")

    data object SignUpScreen : AuthFeatureScreens(route = "sign_up_screen")

    data object LoginScreen : AuthFeatureScreens(route = "login_screen")

    companion object {
        const val navigationRoute = "profile_feature_navigation"
        val startScreenRoute = ProfileScreen.route
    }
}