package com.feature_main.presentation.screens.main

import com.common.ui.state_hoisting.StatefulViewModel
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenAction
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenEffect
import com.lifestylehub.features.main.screens.main.presentation.state_hoisting.MainScreenState

class MainScreenViewModel :
    StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    init {
        updateState(MainScreenState.Content("sdfsfsf"))
    }

    override fun onAction(action: MainScreenAction) {

    }

}