package com.planner.presentation.screens.adding_plan.state_hoisting

sealed class AddingPlanScreenState {

    data class Content(
        val name: String,
        val notes: String,
        val year: Int,
        val month: Int,
        val day: Int,
        val isNameError: Boolean = false,
        val isNotesError: Boolean = false,
    ) : AddingPlanScreenState()
}