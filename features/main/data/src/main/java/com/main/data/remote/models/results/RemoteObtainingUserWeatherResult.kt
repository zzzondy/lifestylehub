package com.main.data.remote.models.results

import com.main.data.remote.models.RemoteWeatherOnUserLocation

sealed class RemoteObtainingUserWeatherResult {

    data class Success(val remoteWeatherOnUserLocation: RemoteWeatherOnUserLocation) :
        RemoteObtainingUserWeatherResult()

    data object LocationError : RemoteObtainingUserWeatherResult()

    data object NetworkError : RemoteObtainingUserWeatherResult()
    data object Error : RemoteObtainingUserWeatherResult()
}