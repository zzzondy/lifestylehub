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

    data object PlanAddingScreen :
        MainFeatureScreens(route = "plan_adding_screen/{${PLACE_DETAILS_SCREEN_ID}}/{${PLAN_ADDING_SCREEN_PLACE_NAME}}") {

        fun passArguments(id: String, placeName: String): String {
            return this.route
                .replace("{${PLACE_DETAILS_SCREEN_ID}}", id)
                .replace("{${PLAN_ADDING_SCREEN_PLACE_NAME}}", placeName)
        }
    }


    companion object {
        const val navigationRoute = "main_feature_navigation"
        val startScreenRoute = MainScreen.route

        const val PLACE_DETAILS_SCREEN_ID = "id"
        const val PLAN_ADDING_SCREEN_PLACE_NAME = "place_name"
    }
}