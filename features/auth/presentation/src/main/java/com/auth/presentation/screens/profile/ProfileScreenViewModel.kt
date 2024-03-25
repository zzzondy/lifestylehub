package com.auth.presentation.screens.profile

import androidx.lifecycle.viewModelScope
import com.auth.domain.use_cases.GetCurrentUserUseCase
import com.auth.domain.use_cases.SignOutUseCase
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenAction
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenEffect
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenState
import com.common.ui.state_hoisting.StatefulViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
) :
    StatefulViewModel<ProfileScreenState, ProfileScreenEffect, ProfileScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, ProfileScreenState.EmptyUser)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentUserUseCase()
                .collectLatest { user ->
                    if (user == null) {
                        updateState(ProfileScreenState.EmptyUser)
                    } else {
                        updateState(ProfileScreenState.Content(user))
                    }
                }
        }
    }

    override fun onAction(action: ProfileScreenAction) {
        when (action) {
            is ProfileScreenAction.OnLoginButtonClicked -> onLoginButtonClicked()

            is ProfileScreenAction.OnSignUpButtonClicked -> onSignUpButtonClicked()

            is ProfileScreenAction.OnSignOutButtonClicked -> onSignOutButtonClicked()
        }
    }

    private fun onLoginButtonClicked() {
        viewModelScope.launch {
            updateEffect(ProfileScreenEffect.NavigateToLoginScreen)
        }
    }

    private fun onSignUpButtonClicked() {
        viewModelScope.launch {
            updateEffect(ProfileScreenEffect.NavigateToSignUpScreen)
        }
    }

    private fun onSignOutButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            signOutUseCase()
        }
    }
}