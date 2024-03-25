package com.auth.presentation.screens.sign_up

import androidx.lifecycle.viewModelScope
import com.auth.domain.results.CreatingUserResult
import com.auth.domain.use_cases.CreateUserUseCase
import com.auth.domain.use_cases.ValidatePasswordUseCase
import com.auth.domain.use_cases.ValidationPasswordResult
import com.auth.presentation.R
import com.auth.presentation.screens.sign_up.state_hoisting.SignUpScreenAction
import com.auth.presentation.screens.sign_up.state_hoisting.SignUpScreenEffect
import com.auth.presentation.screens.sign_up.state_hoisting.SignUpScreenState
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.utils.UIText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SignUpScreenViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
) :
    StatefulViewModel<SignUpScreenState, SignUpScreenEffect, SignUpScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, SignUpScreenState(""))

    override fun onAction(action: SignUpScreenAction) {
        when (action) {
            is SignUpScreenAction.OnUpdatePassword -> onUpdatePassword(action.newPassword)

            is SignUpScreenAction.OnSignUpButtonClicked -> onSignUpButtonClicked()

            is SignUpScreenAction.OnBackButtonClicked -> onBackButtonClicked()
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            updateEffect(SignUpScreenEffect.NavigateBack)
        }
    }

    private fun onUpdatePassword(password: String) {
        viewModelScope.launch(
            Dispatchers.Default
        ) {
            when (validatePasswordUseCase(password)) {
                is ValidationPasswordResult.Success -> {
                    updateState(
                        state.value.copy(
                            password = password,
                            isPasswordFieldError = false,
                            passwordSupportingText = null,
                        )
                    )
                }

                is ValidationPasswordResult.VeryShortPassword -> {
                    updateState(
                        state.value.copy(
                            password = password,
                            isPasswordFieldError = false,
                            passwordSupportingText = UIText.StringResource(R.string.very_short_password),
                        )
                    )
                }

                is ValidationPasswordResult.NotContainsLettersOrDigits -> {
                    updateState(
                        state.value.copy(
                            password = password,
                            isPasswordFieldError = false,
                            passwordSupportingText = UIText.StringResource(R.string.password_should_contains_digits_and_letters),
                        )
                    )
                }

                is ValidationPasswordResult.NotContainsLowerOrUpperCase -> {
                    updateState(
                        state.value.copy(
                            password = password,
                            isPasswordFieldError = false,
                            passwordSupportingText = UIText.StringResource(R.string.password_should_contains_uppercase_and_lowercase),
                        )
                    )
                }
            }
        }
    }

    private fun onSignUpButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            updateEffect(SignUpScreenEffect.ShowLoadingDialog)

            when (validatePasswordUseCase(state.value.password)) {
                is ValidationPasswordResult.Success -> {
                    when (createUserUseCase(state.value.password)) {
                        is CreatingUserResult.Success -> {
                            updateEffect(SignUpScreenEffect.NavigateBack)
                        }

                        is CreatingUserResult.NetworkError -> {
                            updateEffect(SignUpScreenEffect.HideLoadingDialog)
                            updateState(
                                state.value.copy(
                                    errorMessage = UIText.StringResource(R.string.some_error_occurred)
                                )
                            )
                        }

                        is CreatingUserResult.Error -> {
                            updateEffect(SignUpScreenEffect.HideLoadingDialog)
                            updateState(
                                state.value.copy(
                                    errorMessage = UIText.StringResource(R.string.some_error_occurred)
                                )
                            )
                        }
                    }
                }

                is ValidationPasswordResult.VeryShortPassword -> {
                    updateState(
                        state.value.copy(
                            isPasswordFieldError = true,
                            passwordSupportingText = UIText.StringResource(R.string.very_short_password),
                        )
                    )
                    updateEffect(SignUpScreenEffect.HideLoadingDialog)
                }

                is ValidationPasswordResult.NotContainsLettersOrDigits -> {
                    updateState(
                        state.value.copy(
                            isPasswordFieldError = true,
                            passwordSupportingText = UIText.StringResource(R.string.password_should_contains_digits_and_letters),
                        )
                    )
                    updateEffect(SignUpScreenEffect.HideLoadingDialog)
                }

                is ValidationPasswordResult.NotContainsLowerOrUpperCase -> {
                    updateState(
                        state.value.copy(
                            isPasswordFieldError = true,
                            passwordSupportingText = UIText.StringResource(R.string.password_should_contains_uppercase_and_lowercase),
                        )
                    )
                    updateEffect(SignUpScreenEffect.HideLoadingDialog)
                }
            }
        }
    }
}