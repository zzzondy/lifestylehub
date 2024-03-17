package com.main.domain.models.results

import com.main.domain.models.WeatherOnUserLocation

sealed class ObtainingUserWeatherResult {

    data class Success(val weatherOnUserLocation: WeatherOnUserLocation) :
        ObtainingUserWeatherResult()

    data object LocationError : ObtainingUserWeatherResult()

    data object NetworkError : ObtainingUserWeatherResult()

    data object Error : ObtainingUserWeatherResult()
}