package com.feature_main.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.common.ui.extensions.collectAsEffect
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.navigation.MainFeatureScreens
import com.feature_main.presentation.screens.main.components.content.MainScreenContentState
import com.feature_main.presentation.screens.main.components.location_unavailable.MainScreenLocationUnavailableState
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenEffect
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenState

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is MainScreenEffect.NavigateToPlaceDetailsScreen -> {
                navController.navigate(MainFeatureScreens.PlaceDetailsScreen.passArguments(effect.id))
            }
        }
    }

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
                modifier = Modifier.padding(bottom = LifestyleHubTheme.paddings.bottomBarPaddingValue)
            )
        }

        is MainScreenState.LocationUnavailable -> {
            MainScreenLocationUnavailableState(
                isRationaleShowLocationPermissionDialog = state.isRationaleShowLocationPermissionDialog,
                onAction = onAction,
                modifier = Modifier.padding(bottom = LifestyleHubTheme.paddings.bottomBarPaddingValue)
            )
        }
    }
}