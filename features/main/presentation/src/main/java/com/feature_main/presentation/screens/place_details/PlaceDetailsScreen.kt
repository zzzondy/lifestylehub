package com.feature_main.presentation.screens.place_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.common.ui.extensions.collectAsEffect
import com.feature_main.presentation.screens.place_details.components.PlaceDetailsTopAppBar
import com.feature_main.presentation.screens.place_details.components.content.PlaceDetailsScreenContentState
import com.feature_main.presentation.screens.place_details.components.error.PlaceDetailsScreenErrorState
import com.feature_main.presentation.screens.place_details.components.loading.PlaceDetailsScreenLoadingState
import com.feature_main.presentation.screens.place_details.state_hoisting.PlaceDetailsScreenAction
import com.feature_main.presentation.screens.place_details.state_hoisting.PlaceDetailsScreenEffect
import com.feature_main.presentation.screens.place_details.state_hoisting.PlaceDetailsScreenState

@Composable
fun PlaceDetailsScreen(
    navController: NavController,
    viewModel: PlaceDetailsScreenViewModel,
) {
    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is PlaceDetailsScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    PlaceDetailsScreenContent(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
private fun PlaceDetailsScreenContent(
    state: PlaceDetailsScreenState,
    onAction: (PlaceDetailsScreenAction) -> Unit,
) {
    Scaffold(
        topBar = {
            PlaceDetailsTopAppBar(
                onBackButtonClicked = {
                    onAction(PlaceDetailsScreenAction.OnBackButtonClicked)
                }
            )
        }
    ) { paddingValues ->
        when (state) {
            is PlaceDetailsScreenState.Content -> {
                PlaceDetailsScreenContentState(
                    placeDetails = state.placeDetails,
                    onAction = onAction,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )
            }

            is PlaceDetailsScreenState.Loading -> {
                PlaceDetailsScreenLoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )
            }

            is PlaceDetailsScreenState.Error -> {
                PlaceDetailsScreenErrorState(
                    text = state.message.asString(),
                    onTryAgainClicked = { onAction(PlaceDetailsScreenAction.OnTryAgainClicked) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )
            }
        }
    }

}