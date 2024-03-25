package com.main.domain.models.results

import com.main.domain.models.weather.WeatherOnUserLocation

sealed class ObtainingUserWeatherResult {

    data class Success(val weatherOnUserLocation: WeatherOnUserLocation) :
        ObtainingUserWeatherResult()

    data object NetworkError : ObtainingUserWeatherResult()

}