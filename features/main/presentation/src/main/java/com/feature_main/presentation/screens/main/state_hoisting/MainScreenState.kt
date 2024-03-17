package com.feature_main.presentation.screens.main.state_hoisting

import com.main.domain.models.WeatherOnUserLocation

sealed class MainScreenState {

    data object Initial : MainScreenState()

    data class Content(val userWeather: WeatherOnUserLocation?) : MainScreenState()
}