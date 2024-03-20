package com.feature_main.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.feature_main.presentation.screens.main.components.content.MainScreenContentState
import com.feature_main.presentation.screens.main.components.location_unavailable.MainScreenLocationUnavailableState
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenState

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel,
) {
    val state by viewModel.state.collectAsState()

    MainScreenContent(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
private fun MainScreenContent(
    state: MainScreenState,
    onAction: (MainScreenAction) -> Unit,
) {
    when (state) {
        is MainScreenState.Content -> {
            MainScreenContentState(
                weatherSectionState = state.weatherSectionState,
                nearbySectionState = state.nearbySectionState,
                onAction = onAction,
                modifier = Modifier.fillMaxSize()
            )
        }

        is MainScreenState.LocationUnavailable -> {
            MainScreenLocationUnavailableState(
                isRationaleShowLocationPermissionDialog = state.isRationaleShowLocationPermissionDialog,
                onAction = onAction,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}