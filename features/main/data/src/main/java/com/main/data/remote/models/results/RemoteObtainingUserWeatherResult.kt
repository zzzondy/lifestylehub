package com.main.data.remote.models.results

import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation

sealed class RemoteObtainingUserWeatherResult {

    data class Success(val remoteWeatherOnUserLocation: RemoteWeatherOnUserLocation) :
        RemoteObtainingUserWeatherResult()

    data object NetworkError : RemoteObtainingUserWeatherResult()
}