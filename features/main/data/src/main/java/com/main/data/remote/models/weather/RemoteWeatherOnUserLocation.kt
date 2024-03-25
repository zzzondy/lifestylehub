package com.main.data.remote.models.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeatherOnUserLocation(

    @SerialName("weather")
    val iconDetails: List<IconDetails>,

    @SerialName("main")
    val mainWeatherData: MainWeatherData,

    @SerialName("name")
    val currentCity: String,
)