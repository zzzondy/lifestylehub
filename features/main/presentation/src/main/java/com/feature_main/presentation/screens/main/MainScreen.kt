package com.feature_main.presentation.screens.main

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    state: MainScreenState,
    onAction: (MainScreenAction) -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()

    val scaleFraction = if (pullToRefreshState.isRefreshing) 1f else
        LinearOutSlowInEasing.transform(pullToRefreshState.progress).coerceIn(0f, 1f)

    LaunchedEffect(state.isRefreshing) {
        if (state.isRefreshing) {
            pullToRefreshState.startRefresh()
        } else {
            pullToRefreshState.endRefresh()
        }
    }

    LaunchedEffect(key1 = pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) {
            onAction(MainScreenAction.OnRefreshAllDataWithSwipe)
        }
    }

    Box(
        modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        when (state) {
            is MainScreenState.Content -> {
                MainScreenContentState(
                    weatherSectionState = state.weatherSectionState,
                    nearbySectionState = state.nearbySectionState,
                    randomTipSectionState = state.randomTipSectionState,
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

        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction),
        )
    }
}