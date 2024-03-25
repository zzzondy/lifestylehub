package com.feature_main.presentation.screens.main.state_hoisting

sealed class MainScreenAction {

    data object OnLocationPermissionGranted : MainScreenAction()

    data class UpdateLocationPermissionFlag(val isRationaleShow: Boolean) : MainScreenAction()

    data object LoadNextPageOfNearbyPlaces : MainScreenAction()

    data object OnRefreshAllData : MainScreenAction()

    data object OnRefreshAllDataWithSwipe : MainScreenAction()

    data class OnPlaceClicked(val id: String) : MainScreenAction()
}