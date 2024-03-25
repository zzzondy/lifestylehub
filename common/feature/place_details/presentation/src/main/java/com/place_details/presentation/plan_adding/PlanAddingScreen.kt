package com.place_details.presentation.plan_adding

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
import com.place_details.presentation.plan_adding.state_hoisting.PlanAddingScreenAction
import com.place_details.presentation.plan_adding.state_hoisting.PlanAddingScreenEffect

@Composable
fun PlanAddingScreen(
    navController: NavController,
    viewModel: PlanAddingScreenViewModel,
) {
    var isLoadingDialogVisible by remember {
        mutableStateOf(false)
    }

    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is PlanAddingScreenEffect.ShowLoadingDialog -> {
                isLoadingDialogVisible = true
            }

            is PlanAddingScreenEffect.HideLoadingDialog -> {
                isLoadingDialogVisible = false
            }

            is PlanAddingScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    if (isLoadingDialogVisible) {
        LoadingDialog()
    }

    Scaffold(
        topBar = {
            PlanAddingTopAppBar(
                onBackButtonClicked = { viewModel.onAction(PlanAddingScreenAction.OnBackButtonClicked) }
            )
        }
    ) { paddingValues ->
        PlanAddingScreenContent(
            name = state.name,
            placeName = state.placeName,
            isNameError = state.isNameError,
            year = state.year,
            month = state.month,
            day = state.day,
            onAction = viewModel::onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}