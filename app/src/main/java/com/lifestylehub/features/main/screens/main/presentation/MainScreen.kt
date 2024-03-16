package com.lifestylehub.features.main.screens.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenState

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState(initial = MainScreenState.Initial).value
}

@Composable
private fun MainScreenContent(
) {
}