package com.planner.presentation.navigation

sealed class PlannerFeatureScreens(
    val route: String
) {

    data object PlansListScreen : PlannerFeatureScreens(route = "plans_list_screen")

    data object AddingPlanScreen : PlannerFeatureScreens(route = "adding_plan_screen")

    data object PlaceDetailsScreen :
        PlannerFeatureScreens(route = "place_details_screen_planner/{${PLACE_DETAILS_SCREEN_ID}}") {

        fun passArguments(id: String): String {
            return this.route.replace("{${PLACE_DETAILS_SCREEN_ID}}", id)
        }
    }

    companion object {
        const val navigationRoute = "planner_feature_navigation"
        val startScreenDestination = PlansListScreen.route

        const val PLACE_DETAILS_SCREEN_ID = "id"
    }
}