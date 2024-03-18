package com.feature_main.presentation.screens.main.state_hoisting

import com.common.ui.utils.UIText
import com.main.domain.models.WeatherOnUserLocation

sealed class MainScreenState {

    data class Content(
        val userWeather: WeatherOnUserLocation?,
        val isRationaleShowLocationPermissionDialog: Boolean,
        val errorText: UIText? = null,
    ) : MainScreenState()

    data object Loading : MainScreenState()
}