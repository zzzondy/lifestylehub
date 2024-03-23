package com.planner.presentation.screens.adding_plan.state_hoisting

sealed class AddingPlanScreenAction {

    data class OnNameUpdated(val newName: String) : AddingPlanScreenAction()

    data class OnNotesUpdated(val newNotes: String) : AddingPlanScreenAction()

    data class OnDateUpdated(val year: Int, val month: Int, val day: Int) : AddingPlanScreenAction()

    data object OnAddButtonClicked : AddingPlanScreenAction()

    data object OnBackButtonClicked : AddingPlanScreenAction()
}