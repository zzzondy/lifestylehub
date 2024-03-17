package com.main.data.utils.mappers

import com.main.data.remote.models.RemoteWeatherOnUserLocation
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.domain.models.WeatherOnUserLocation
import com.main.domain.models.results.ObtainingUserWeatherResult

fun RemoteObtainingUserWeatherResult.toDomain() = when (this) {
    is RemoteObtainingUserWeatherResult.Success -> ObtainingUserWeatherResult.Success(this.remoteWeatherOnUserLocation.toDomain())

    is RemoteObtainingUserWeatherResult.Error -> ObtainingUserWeatherResult.Error

    is RemoteObtainingUserWeatherResult.LocationError -> ObtainingUserWeatherResult.LocationError

    is RemoteObtainingUserWeatherResult.NetworkError -> ObtainingUserWeatherResult.NetworkError
}

private fun RemoteWeatherOnUserLocation.toDomain() =
    WeatherOnUserLocation(
        temperature = this.mainWeatherData.temperature,
        icon = this.iconDetails[0].icon,
        minTemperature = this.mainWeatherData.minTemperature,
        maxTemperature = this.mainWeatherData.maxTemperature,
        temperatureFeelsLike = this.mainWeatherData.feelingTemperature,
        weatherCondition = this.iconDetails[0].description,
        currentCity = this.currentCity
    )