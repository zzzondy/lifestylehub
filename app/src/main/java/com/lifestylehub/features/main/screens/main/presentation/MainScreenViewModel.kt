package com.lifestylehub.features.main.screens.main.presentation

import com.lifestylehub.common.state_hoisting.StatefulViewModel
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenAction
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenEffect
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() :
    StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    init {
        updateState(MainScreenState.Content("sdfsfsf"))
    }

    override fun onAction(action: MainScreenAction) {

    }

}