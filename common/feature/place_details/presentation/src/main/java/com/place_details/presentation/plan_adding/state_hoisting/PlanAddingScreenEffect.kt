package com.place_details.presentation.plan_adding.state_hoisting

sealed class PlanAddingScreenEffect {

    data object ShowLoadingDialog : PlanAddingScreenEffect()

    data object HideLoadingDialog : PlanAddingScreenEffect()

    data object NavigateBack : PlanAddingScreenEffect()
}