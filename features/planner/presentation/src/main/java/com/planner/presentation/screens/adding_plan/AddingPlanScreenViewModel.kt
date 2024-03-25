package com.planner.presentation.screens.adding_plan

import androidx.lifecycle.viewModelScope
import com.common.ui.state_hoisting.StatefulViewModel
import com.planner.domain.models.Plan
import com.planner.domain.use_cases.AddPlanUseCase
import com.planner.presentation.screens.adding_plan.state_hoisting.AddingPlanScreenAction
import com.planner.presentation.screens.adding_plan.state_hoisting.AddingPlanScreenEffect
import com.planner.presentation.screens.adding_plan.state_hoisting.AddingPlanScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class AddingPlanScreenViewModel(
    private val addPlanUseCase: AddPlanUseCase,
) :
    StatefulViewModel<AddingPlanScreenState, AddingPlanScreenEffect, AddingPlanScreenAction>() {

    private val calendar = Calendar.getInstance()

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            AddingPlanScreenState.Content(
                "",
                "",
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH] + 1,
                calendar[Calendar.DAY_OF_MONTH]
            )
        )


    override fun onAction(action: AddingPlanScreenAction) {
        when (action) {
            is AddingPlanScreenAction.OnDateUpdated -> {
                onDateUpdated(action.year, action.month, action.day)
            }

            is AddingPlanScreenAction.OnNotesUpdated -> {
                onNotesUpdated(action.newNotes)
            }

            is AddingPlanScreenAction.OnNameUpdated -> {
                onNameUpdated(action.newName)
            }

            is AddingPlanScreenAction.OnAddButtonClicked -> {
                onAddButtonClicked()
            }

            is AddingPlanScreenAction.OnBackButtonClicked -> {
                onBackButtonClicked()
            }
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            updateEffect(AddingPlanScreenEffect.NavigateBack)
        }
    }

    private fun onNameUpdated(newName: String) {
        viewModelScope.launch {
            if (state.value is AddingPlanScreenState.Content) {
                updateState(
                    AddingPlanScreenState.Content(
                        name = newName,
                        notes = (state.value as AddingPlanScreenState.Content).notes,
                        year = (state.value as AddingPlanScreenState.Content).year,
                        month = (state.value as AddingPlanScreenState.Content).month,
                        day = (state.value as AddingPlanScreenState.Content).day,
                        isNameError = newName.isEmpty(),
                        isNotesError = (state.value as AddingPlanScreenState.Content).isNotesError,
                    )
                )
            }
        }
    }

    private fun onNotesUpdated(newNotes: String) {
        viewModelScope.launch {
            if (state.value is AddingPlanScreenState.Content) {
                updateState(
                    AddingPlanScreenState.Content(
                        name = (state.value as AddingPlanScreenState.Content).name,
                        notes = newNotes,
                        year = (state.value as AddingPlanScreenState.Content).year,
                        month = (state.value as AddingPlanScreenState.Content).month,
                        day = (state.value as AddingPlanScreenState.Content).day,
                        isNameError = (state.value as AddingPlanScreenState.Content).isNameError,
                        isNotesError = newNotes.isEmpty(),
                    )
                )
            }
        }
    }

    private fun onDateUpdated(year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        viewModelScope.launch {
            updateState(
                AddingPlanScreenState.Content(
                    name = (state.value as AddingPlanScreenState.Content).name,
                    notes = (state.value as AddingPlanScreenState.Content).notes,
                    year = year,
                    month = month + 1,
                    day = day,
                    isNameError = (state.value as AddingPlanScreenState.Content).isNameError,
                    isNotesError = (state.value as AddingPlanScreenState.Content).isNotesError,
                )
            )
        }
    }

    private fun onAddButtonClicked() {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            updateEffect(AddingPlanScreenEffect.ShowLoadingDialog)
            if (state.value is AddingPlanScreenState.Content) {
                val isNameError = (state.value as AddingPlanScreenState.Content).name.isEmpty()
                val isNotesError = (state.value as AddingPlanScreenState.Content).notes.isEmpty()

                if (isNameError || isNotesError) {
                    updateState(
                        AddingPlanScreenState.Content(
                            name = (state.value as AddingPlanScreenState.Content).name,
                            notes = (state.value as AddingPlanScreenState.Content).notes,
                            year = (state.value as AddingPlanScreenState.Content).year,
                            month = (state.value as AddingPlanScreenState.Content).month,
                            day = (state.value as AddingPlanScreenState.Content).day,
                            isNameError = isNameError,
                            isNotesError = isNotesError,
                        )
                    )
                    updateEffect(AddingPlanScreenEffect.HideLoadingDialog)
                } else {
                    addPlanUseCase(fromStateToPlan())
                    updateEffect(AddingPlanScreenEffect.NavigateBack)
                }
            }
        }
    }

    private fun fromStateToPlan(): Plan =
        Plan(
            name = (state.value as AddingPlanScreenState.Content).name,
            notes = (state.value as AddingPlanScreenState.Content).notes,
            date = "${(state.value as AddingPlanScreenState.Content).day} ${(state.value as AddingPlanScreenState.Content).month} ${(state.value as AddingPlanScreenState.Content).year}",
            placeName = null,
            placeId = null,
        )
}