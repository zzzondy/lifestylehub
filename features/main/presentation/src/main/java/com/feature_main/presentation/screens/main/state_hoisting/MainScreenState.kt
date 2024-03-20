package com.feature_main.presentation.screens.main.state_hoisting

import com.common.ui.utils.UIText
import com.main.domain.models.places.PagingItem
import com.main.domain.models.weather.WeatherOnUserLocation

sealed class MainScreenState {

    data class Content(
        val weatherSectionState: WeatherSectionState,
        val nearbySectionState: NearbySectionState,
    ) : MainScreenState()

    data class LocationUnavailable(
        val isRationaleShowLocationPermissionDialog: Boolean
    ) : MainScreenState()
}

sealed class WeatherSectionState {

    data class Data(val userWeather: WeatherOnUserLocation) : WeatherSectionState()

    data class Error(val message: UIText) : WeatherSectionState()

    data object Loading : WeatherSectionState()
}

sealed class NearbySectionState {

    data class Data(
        val places: List<PagingItem>,
        val errorOnAddingNewPlaces: UIText? = null,
        val loadingOnAddingNewPlaces: Boolean = false,
        val dataLimitReached: Boolean = false,
    ) : NearbySectionState()

    data object Empty : NearbySectionState()

    data class NetworkError(val message: UIText) : NearbySectionState()

    data object Loading : NearbySectionState()
}