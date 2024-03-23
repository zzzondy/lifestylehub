package com.planner.presentation.screens.plans_screen.state_hoisting

sealed class PlansScreenEffect {

    data object NavigateToAddPlanScreen : PlansScreenEffect()

    data class NavigateToPlaceDetailsScreen(val placeId: String) : PlansScreenEffect()
}