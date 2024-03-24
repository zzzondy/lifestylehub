package com.auth.presentation.screens.login

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
import com.auth.presentation.screens.login.state_hoisting.LoginScreenAction
import com.auth.presentation.screens.login.state_hoisting.LoginScreenEffect
import com.common.ui.components.LoadingDialog
import com.common.ui.extensions.collectAsEffect

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel,
) {
    var isLoadingDialogVisible by remember {
        mutableStateOf(false)
    }

    val state by viewModel.state.collectAsState()
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is LoginScreenEffect.ShowLoadingDialog -> {
                isLoadingDialogVisible = true
            }

            is LoginScreenEffect.HideLoadingDialog -> {
                isLoadingDialogVisible = false
            }

            is LoginScreenEffect.NavigateBack -> {
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
            LoginScreenTopAppBar(onBackButtonClicked = { viewModel.onAction(LoginScreenAction.OnBackButtonClicked) })
        }
    ) { paddingValues ->
        LoginScreenContent(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}