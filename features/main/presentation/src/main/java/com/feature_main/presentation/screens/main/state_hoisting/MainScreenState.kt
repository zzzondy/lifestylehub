package com.feature_main.presentation.screens.main.state_hoisting

import com.common.ui.utils.UIText
import com.main.domain.models.places.PagingItem
import com.main.domain.models.tips.RandomTip
import com.main.domain.models.weather.WeatherOnUserLocation

sealed class MainScreenState(open val isRefreshing: Boolean) {

    data class Content(
        val weatherSectionState: WeatherSectionState,
        val nearbySectionState: NearbySectionState,
        val randomTipSectionState: RandomTipSectionState,
        override val isRefreshing: Boolean,
    ) : MainScreenState(isRefreshing)

    data class LocationUnavailable(
        val isRationaleShowLocationPermissionDialog: Boolean,
        override val isRefreshing: Boolean
    ) : MainScreenState(isRefreshing)
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

sealed class RandomTipSectionState {

    data class Data(
        val tip: RandomTip,
    ) : RandomTipSectionState()

    data class Error(val message: UIText) : RandomTipSectionState()

    data object Loading : RandomTipSectionState()
}