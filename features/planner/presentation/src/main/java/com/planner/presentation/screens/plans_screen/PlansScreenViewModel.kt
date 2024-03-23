package com.planner.presentation.screens.plans_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.utils.UIText
import com.planner.domain.models.Plan
import com.planner.domain.results.ObtainingAllPlansResult
import com.planner.domain.use_cases.DeletePlanByIdUseCase
import com.planner.domain.use_cases.GetAllPlansUseCase
import com.planner.presentation.R
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenAction
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenEffect
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlansScreenViewModel(
    private val getAllPlansUseCase: GetAllPlansUseCase,
    private val deletePlanByIdUseCase: DeletePlanByIdUseCase,
) :
    StatefulViewModel<PlansScreenState, PlansScreenEffect, PlansScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, PlansScreenState.Loading)

    private val plansList = mutableListOf<Plan>()


    override fun onAction(action: PlansScreenAction) {
        when (action) {
            is PlansScreenAction.OnAddPlanClicked -> onAddPlanClicked()

            is PlansScreenAction.OnTryAgain -> getAllPlans()

            is PlansScreenAction.OnScreenEntered -> getAllPlans()

            is PlansScreenAction.OnDeletePlan -> onDeletePlan(action.id)

            is PlansScreenAction.OnPlanWithPlaceClicked -> onPlanWithPlaceClicked(action.placeId)
        }
    }

    private fun onPlanWithPlaceClicked(placeId: String) {
        viewModelScope.launch {
            updateEffect(PlansScreenEffect.NavigateToPlaceDetailsScreen(placeId))
        }
    }

    private fun onAddPlanClicked() {
        viewModelScope.launch {
            updateEffect(PlansScreenEffect.NavigateToAddPlanScreen)
        }
    }

    private fun getAllPlans() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(PlansScreenState.Loading)
            when (val result = getAllPlansUseCase()) {
                is ObtainingAllPlansResult.Success -> {
                    if (result.plans.isEmpty()) {
                        updateState(PlansScreenState.EmptyList)
                    } else {
                        plansList.clear()
                        plansList.addAll(result.plans)
                        updateState(PlansScreenState.Content(plansList.toList()))
                    }
                }

                is ObtainingAllPlansResult.Error -> {
                    PlansScreenState.Error(UIText.StringResource(R.string.some_error))
                }
            }
        }
    }

    private fun onDeletePlan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePlanByIdUseCase(id)
            Log.d("Planner", id.toString())
            plansList.removeIf { it.id == id }
            Log.d("Planner", plansList.toString())
            updateStateWithPlansList()
        }
    }

    private fun updateStateWithPlansList() {
        viewModelScope.launch {
            if (plansList.isEmpty()) {
                updateState(PlansScreenState.EmptyList)
            } else {
                updateState(PlansScreenState.Content(plansList.toList()))
            }
        }
    }
}