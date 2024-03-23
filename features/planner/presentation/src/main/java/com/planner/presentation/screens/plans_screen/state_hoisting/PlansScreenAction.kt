package com.planner.presentation.screens.plans_screen.state_hoisting

sealed class PlansScreenAction {

    data object OnAddPlanClicked : PlansScreenAction()

    data object OnTryAgain : PlansScreenAction()

    data object OnScreenEntered : PlansScreenAction()

    data class OnDeletePlan(val id: Long) : PlansScreenAction()

    data class OnPlanWithPlaceClicked(val placeId: String) : PlansScreenAction()

}