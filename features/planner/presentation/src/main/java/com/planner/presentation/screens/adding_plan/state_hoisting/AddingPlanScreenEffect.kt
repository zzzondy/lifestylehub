package com.planner.presentation.screens.adding_plan.state_hoisting

sealed class AddingPlanScreenEffect {

    data object ShowLoadingDialog : AddingPlanScreenEffect()

    data object HideLoadingDialog : AddingPlanScreenEffect()

    data object NavigateBack : AddingPlanScreenEffect()
}