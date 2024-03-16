package com.lifestylehub.features.main.screens.main.presentation.state_hoisting

sealed class MainScreenState {

    data object Initial : MainScreenState()

    data class Content(val name: String = "hello") : MainScreenState()
}