package com.main.domain.models

data class WeatherOnUserLocation(
    val temperature: Double,
    val icon: String,
    val minTemperature: Double,
    val maxTemperature: Double,
    val temperatureFeelsLike: Double,
    val weatherCondition: String,
    val currentCity: String,
)
