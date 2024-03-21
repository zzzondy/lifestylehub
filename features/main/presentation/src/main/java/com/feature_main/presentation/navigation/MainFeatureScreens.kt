package com.feature_main.presentation.navigation

sealed class MainFeatureScreens(val route: String) {

    data object MainScreen : MainFeatureScreens(route = "main_screen")

    data object PlaceDetailsScreen :
        MainFeatureScreens(route = "place_details_screen/{${PLACE_DETAILS_SCREEN_ID}}") {

        fun passArguments(
            id: String,
        ): String {
            return this.route
                .replace("{$PLACE_DETAILS_SCREEN_ID}", id)
        }
    }


    companion object {
        const val navigationRoute = "main_feature_navigation"
        val startScreenRoute = MainScreen.route

        const val PLACE_DETAILS_SCREEN_ID = "id"
    }
}