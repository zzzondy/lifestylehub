package com.place_details.presentation.place_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.common.ui.extensions.collectAsEffect
import com.place_details.presentation.place_details.components.PlaceDetailsTopAppBar
import com.place_details.presentation.place_details.components.content.PlaceDetailsScreenContentState
import com.place_details.presentation.place_details.components.error.PlaceDetailsScreenErrorState
import com.place_details.presentation.place_details.components.loading.PlaceDetailsScreenLoadingState
import com.place_details.presentation.place_details.state_hoisting.PlaceDetailsScreenAction
import com.place_details.presentation.place_details.state_hoisting.PlaceDetailsScreenEffect
import com.place_details.presentation.place_details.state_hoisting.PlaceDetailsScreenState

@Composable
fun PlaceDetailsScreen(
    navController: NavController,
    viewModel: PlaceDetailsScreenViewModel,
    shouldToShowAddPlanButton: Boolean,
    onAddPlanButtonClicked: ((String, String) -> String)? = null
) {
    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is PlaceDetailsScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }

            is PlaceDetailsScreenEffect.NavigateToAddPlanScreen -> {
                if (shouldToShowAddPlanButton && onAddPlanButtonClicked != null) {
                    navController.navigate(onAddPlanButtonClicked(effect.placeId, effect.placeName))
                }
            }
        }
    }

    PlaceDetailsScreenContent(
        state = state,
        onAction = viewModel::onAction,
        shouldToShowAddPlanButton = shouldToShowAddPlanButton,
    )
}

@Composable
private fun PlaceDetailsScreenContent(
    state: PlaceDetailsScreenState,
    onAction: (PlaceDetailsScreenAction) -> Unit,
    shouldToShowAddPlanButton: Boolean
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
                    shouldToShowAddPlanButton = shouldToShowAddPlanButton,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding()),
                    onAction = onAction
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