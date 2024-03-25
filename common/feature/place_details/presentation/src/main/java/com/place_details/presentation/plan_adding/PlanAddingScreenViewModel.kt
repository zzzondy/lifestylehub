package com.place_details.presentation.plan_adding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.place_details.domain.models.planner.Plan
import com.place_details.domain.use_cases.AddNewPlanUseCase
import com.place_details.presentation.plan_adding.state_hoisting.PlanAddingScreenAction
import com.place_details.presentation.plan_adding.state_hoisting.PlanAddingScreenEffect
import com.place_details.presentation.plan_adding.state_hoisting.PlanAddingScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class PlanAddingScreenViewModel(
    private val placeId: String,
    private val placeName: String,
    private val addNewPlanUseCase: AddNewPlanUseCase,
) :
    StatefulViewModel<PlanAddingScreenState, PlanAddingScreenEffect, PlanAddingScreenAction>() {

    private val calendar = Calendar.getInstance()

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            PlanAddingScreenState(
                name = "",
                placeName = placeName,
                year = calendar[Calendar.YEAR],
                month = calendar[Calendar.MONTH] + 1,
                day = calendar[Calendar.DAY_OF_MONTH]
            )
        )

    override fun onAction(action: PlanAddingScreenAction) {
        when (action) {
            is PlanAddingScreenAction.OnUpdateName -> {
                onUpdateName(action.newName)
            }

            is PlanAddingScreenAction.OnUpdateDate -> {
                onUpdateDate(action.year, action.month, action.day)
            }

            is PlanAddingScreenAction.OnBackButtonClicked -> {
                onBackButtonClicked()
            }

            is PlanAddingScreenAction.OnAddButtonClicked -> {
                onAddButtonClicked()
            }
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            updateEffect(PlanAddingScreenEffect.NavigateBack)
        }
    }

    private fun onUpdateName(newName: String) {
        viewModelScope.launch {
            updateState(
                state.value.copy(
                    name = newName,
                    isNameError = newName.isEmpty()
                )
            )
        }
    }

    private fun onUpdateDate(year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        viewModelScope.launch {
            updateState(
                state.value.copy(
                    year = year,
                    month = month + 1,
                    day = day
                )
            )
        }
    }

    private fun onAddButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val isNameError = state.value.name.isEmpty()
            if (isNameError) {
                updateEffect(PlanAddingScreenEffect.ShowLoadingDialog)
                updateState(
                    state.value.copy(
                        isNameError = true
                    )
                )
                updateEffect(PlanAddingScreenEffect.HideLoadingDialog)
            } else {
                addNewPlanUseCase(fromStateToPlan())
                updateEffect(PlanAddingScreenEffect.NavigateBack)
            }
        }
    }

    private fun fromStateToPlan(): Plan =
        Plan(
            name = state.value.name,
            date = "${state.value.day} ${state.value.month} ${state.value.year}",
            placeName = placeName,
            placeId = placeId,
        )

    class Factory(
        private val placeId: String,
        private val placeName: String,
        private val addNewPlanUseCase: AddNewPlanUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == PlanAddingScreenViewModel::class.java)
            return PlanAddingScreenViewModel(placeId, placeName, addNewPlanUseCase) as T
        }
    }
}

class PlanAddingScreenViewModelFactoryFactory(
    private val addNewPlanUseCase: AddNewPlanUseCase,
) {

    fun create(placeId: String, placeName: String): PlanAddingScreenViewModel.Factory {
        return PlanAddingScreenViewModel.Factory(placeId, placeName, addNewPlanUseCase)
    }
}

