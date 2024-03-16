package com.feature_main.presentation.navigation

sealed class MainFeatureScreens(val route: String) {

    data object MainScreen : MainFeatureScreens(route = "main_screen")


    companion object {
        const val navigationRoute = "main_feature_navigation"
        val startScreenRoute = MainScreen.route
    }
}