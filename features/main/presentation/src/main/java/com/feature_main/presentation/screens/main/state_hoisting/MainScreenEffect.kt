package com.feature_main.presentation.screens.main.state_hoisting

sealed class MainScreenEffect {

    data class NavigateToPlaceDetailsScreen(val id: String) :
        MainScreenEffect()
}