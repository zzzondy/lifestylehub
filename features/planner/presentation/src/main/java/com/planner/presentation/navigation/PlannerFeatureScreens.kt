package com.planner.presentation.navigation

sealed class PlannerFeatureScreens(
    val route: String
) {

    data object PlansListScreen : PlannerFeatureScreens(route = "plans_list_screen")

    data object AddingPlanScreen : PlannerFeatureScreens(route = "adding_plan_screen")

    companion object {
        const val navigationRoute = "planner_feature_navigation"
        val startScreenDestination = PlansListScreen.route
    }
}