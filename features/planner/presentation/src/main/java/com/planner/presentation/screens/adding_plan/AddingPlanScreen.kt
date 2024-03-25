package com.planner.presentation.screens.adding_plan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.common.ui.components.LoadingDialog
import com.common.ui.extensions.collectAsEffect
import com.planner.presentation.screens.adding_plan.state_hoisting.AddingPlanScreenAction
import com.planner.presentation.screens.adding_plan.state_hoisting.AddingPlanScreenEffect
import com.planner.presentation.screens.adding_plan.state_hoisting.AddingPlanScreenState

@Composable
fun AddingPlanScreen(
    navController: NavController,
    viewModel: AddingPlanScreenViewModel,
) {
    var isLoadingDialogVisible by remember {
        mutableStateOf(false)
    }

    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is AddingPlanScreenEffect.ShowLoadingDialog -> {
                isLoadingDialogVisible = true
            }

            is AddingPlanScreenEffect.HideLoadingDialog -> {
                isLoadingDialogVisible = false
            }

            is AddingPlanScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    if (isLoadingDialogVisible) {
        LoadingDialog()
    }

    AddingPlanScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun AddingPlanScreenContent(
    state: AddingPlanScreenState,
    onAction: (AddingPlanScreenAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AddingPlanScreenTopAppBar(onBackButtonClicked = { onAction(AddingPlanScreenAction.OnBackButtonClicked) })
        }
    ) { paddingValues ->
        when (state) {
            is AddingPlanScreenState.Content -> {
                AddingPlanScreenContentState(
                    name = state.name,
                    notes = state.notes,
                    year = state.year,
                    month = state.month,
                    day = state.day,
                    isNameError = state.isNameError,
                    isNotesError = state.isNotesError,
                    onAction = onAction,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}