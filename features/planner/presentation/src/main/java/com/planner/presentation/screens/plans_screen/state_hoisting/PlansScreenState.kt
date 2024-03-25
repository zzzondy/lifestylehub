package com.planner.presentation.screens.plans_screen.state_hoisting

import com.common.ui.utils.UIText
import com.planner.domain.models.Plan

sealed class PlansScreenState {

    data object Loading : PlansScreenState()

    data class Content(val plans: List<Plan>) : PlansScreenState()

    data object EmptyList : PlansScreenState()

    data class Error(val message: UIText) : PlansScreenState()
}