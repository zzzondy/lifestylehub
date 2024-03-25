package com.main.data.remote.models.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainWeatherData(
    @SerialName("temp")
    val temperature: Double,

    @SerialName("feels_like")
    val feelingTemperature: Double,

    @SerialName("temp_min")
    val minTemperature: Double,

    @SerialName("temp_max")
    val maxTemperature: Double,
)
