package com.feature_main.presentation.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenState

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel,
) {
    val state by viewModel.state.collectAsState(initial = MainScreenState.Initial)

    when (state) {
        is MainScreenState.Content -> {
            Text(text = (state as MainScreenState.Content).name)
        }

        else -> {}
    }
}

@Composable
private fun MainScreenContent(
) {
}