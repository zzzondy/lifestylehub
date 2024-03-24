package com.auth.presentation.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.auth.presentation.navigation.AuthFeatureScreens
import com.auth.presentation.screens.profile.components.content.ProfileScreenContentState
import com.auth.presentation.screens.profile.components.empty_user.ProfileScreenEmptyUserState
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenAction
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenEffect
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenState
import com.common.ui.extensions.collectAsEffect
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileScreenViewModel,
) {
    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is ProfileScreenEffect.NavigateToLoginScreen -> {
                navController.navigate(AuthFeatureScreens.LoginScreen.route)
            }

            is ProfileScreenEffect.NavigateToSignUpScreen -> {
                navController.navigate(AuthFeatureScreens.SignUpScreen.route)
            }
        }
    }

    ProfileScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ProfileScreenContent(
    state: ProfileScreenState,
    onAction: (ProfileScreenAction) -> Unit,
) {
    when (state) {
        is ProfileScreenState.Content -> {
            ProfileScreenContentState(
                user = state.user,
                onAction = onAction,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LifestyleHubTheme.paddings.bottomBarPaddingValue)
            )
        }

        is ProfileScreenState.EmptyUser -> {
            ProfileScreenEmptyUserState(
                onAction = onAction,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LifestyleHubTheme.paddings.bottomBarPaddingValue)
            )
        }
    }
}