package com.place_details.presentation.plan_adding.state_hoisting

data class PlanAddingScreenState(
    val name: String,
    val placeName: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val isNameError: Boolean = false,
)