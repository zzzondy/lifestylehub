package com.place_details.presentation.plan_adding.state_hoisting

sealed class PlanAddingScreenAction {

    data class OnUpdateName(val newName: String) : PlanAddingScreenAction()

    data class OnUpdateDate(val year: Int, val month: Int, val day: Int) : PlanAddingScreenAction()

    data object OnAddButtonClicked : PlanAddingScreenAction()

    data object OnBackButtonClicked : PlanAddingScreenAction()
}