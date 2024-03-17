package com.feature_main.presentation.screens.main.state_hoisting

sealed class MainScreenAction {

    data object OnLocationPermissionGranted : MainScreenAction()
}