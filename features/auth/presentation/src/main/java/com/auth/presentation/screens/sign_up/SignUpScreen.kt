package com.auth.presentation.screens.sign_up

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.auth.presentation.screens.sign_up.state_hoisting.SignUpScreenAction
import com.auth.presentation.screens.sign_up.state_hoisting.SignUpScreenEffect
import com.common.ui.components.LoadingDialog
import com.common.ui.extensions.collectAsEffect

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpScreenViewModel,
) {
    var isLoadingDialogVisible by remember {
        mutableStateOf(false)
    }

    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is SignUpScreenEffect.ShowLoadingDialog -> {
                isLoadingDialogVisible = true
            }

            is SignUpScreenEffect.HideLoadingDialog -> {
                isLoadingDialogVisible = false
            }

            is SignUpScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    if (isLoadingDialogVisible) {
        LoadingDialog()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SignUpScreenTopAppBar(
                onBackButtonClicked = { viewModel.onAction(SignUpScreenAction.OnBackButtonClicked) }
            )
        }
    ) { paddingValues ->
        SignUpScreenContent(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}