package com.planner.presentation.screens.plans_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.common.ui.extensions.collectAsEffect
import com.common.ui.theme.LifestyleHubTheme
import com.planner.presentation.R
import com.planner.presentation.navigation.PlannerFeatureScreens
import com.planner.presentation.screens.plans_screen.components.content.PlansScreenContentState
import com.planner.presentation.screens.plans_screen.components.empty.PlansScreenEmptyState
import com.planner.presentation.screens.plans_screen.components.error.PlansScreenErrorState
import com.planner.presentation.screens.plans_screen.components.loading.PlansScreenLoadingState
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenAction
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenEffect
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenState

@Composable
fun PlansScreen(
    navController: NavController,
    viewModel: PlansScreenViewModel,
) {
    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is PlansScreenEffect.NavigateToAddPlanScreen -> {
                navController.navigate(PlannerFeatureScreens.AddingPlanScreen.route)
            }

            is PlansScreenEffect.NavigateToPlaceDetailsScreen -> {
                navController.navigate(PlannerFeatureScreens.PlaceDetailsScreen.passArguments(effect.placeId))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onAction(PlansScreenAction.OnScreenEntered)
    }

    PlansScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun PlansScreenContent(
    state: PlansScreenState,
    onAction: (PlansScreenAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = LifestyleHubTheme.paddings.bottomBarPaddingValue)
    ) {
        when (state) {
            is PlansScreenState.EmptyList -> {
                PlansScreenEmptyState(
                    onAction = onAction,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is PlansScreenState.Content -> {
                PlansScreenContentState(
                    plans = state.plans,
                    modifier = Modifier.fillMaxSize(),
                    onAction = onAction
                )
            }

            is PlansScreenState.Loading -> {
                PlansScreenLoadingState(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is PlansScreenState.Error -> {
                PlansScreenErrorState(
                    errorMessage = state.message.asString(),
                    onAction = onAction,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        FloatingActionButton(
            onClick = { onAction(PlansScreenAction.OnAddPlanClicked) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = LifestyleHubTheme.paddings.medium,
                    bottom = LifestyleHubTheme.paddings.medium
                )
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(R.string.add)
            )
        }
    }
}