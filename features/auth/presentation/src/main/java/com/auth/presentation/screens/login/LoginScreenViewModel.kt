package com.auth.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.auth.domain.results.LoginResult
import com.auth.domain.use_cases.LoginUseCase
import com.auth.presentation.R
import com.auth.presentation.screens.login.state_hoisting.LoginScreenAction
import com.auth.presentation.screens.login.state_hoisting.LoginScreenEffect
import com.auth.presentation.screens.login.state_hoisting.LoginScreenState
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.utils.UIText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginUseCase: LoginUseCase,
) : StatefulViewModel<LoginScreenState, LoginScreenEffect, LoginScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, LoginScreenState("", ""))

    override fun onAction(action: LoginScreenAction) {
        when (action) {
            is LoginScreenAction.OnLoginUpdate -> onLoginUpdate(action.newLogin)

            is LoginScreenAction.OnPasswordUpdate -> onPasswordUpdate(action.newPassword)

            is LoginScreenAction.OnLoginButtonClicked -> onLoginButtonClicked()

            is LoginScreenAction.OnBackButtonClicked -> onBackButtonClicked()
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            updateEffect(LoginScreenEffect.NavigateBack)
        }
    }

    private fun onLoginUpdate(login: String) {
        viewModelScope.launch {
            updateState(
                state.value.copy(
                    login = login,
                    errorMessage = null,
                )
            )
        }
    }

    private fun onPasswordUpdate(password: String) {
        viewModelScope.launch {
            updateState(
                state.value.copy(
                    password = password,
                    errorMessage = null,
                )
            )
        }
    }

    private fun onLoginButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            updateEffect(LoginScreenEffect.ShowLoadingDialog)
            when (loginUseCase(state.value.login, state.value.password)) {
                is LoginResult.Success -> {
                    updateEffect(LoginScreenEffect.NavigateBack)
                }

                is LoginResult.WrongLoginOrPassword -> {
                    updateEffect(LoginScreenEffect.HideLoadingDialog)
                    updateState(
                        state.value.copy(
                            errorMessage = UIText.StringResource(R.string.wrong_password_or_login)
                        )
                    )
                }

                is LoginResult.Error -> {
                    updateEffect(LoginScreenEffect.HideLoadingDialog)
                    updateState(
                        state.value.copy(
                            errorMessage = UIText.StringResource(R.string.some_error_occurred)
                        )
                    )
                }
            }
        }
    }
}