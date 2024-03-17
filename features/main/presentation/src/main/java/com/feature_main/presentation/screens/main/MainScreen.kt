package com.feature_main.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.feature_main.presentation.screens.main.components.MainScreenContentState
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenState

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel,
) {
    val state by viewModel.state.collectAsState(initial = MainScreenState.Initial)
    MainScreenContent(
        state = state,
        onAction = viewModel::onAction
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
                userWeather = state.userWeather,
                onAction = onAction,
                modifier = Modifier.fillMaxSize()
            )
        }

        else -> {

        }
    }
}