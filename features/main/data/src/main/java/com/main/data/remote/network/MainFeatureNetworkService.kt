package com.main.data.remote.network

import com.main.data.BuildConfig
import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainFeatureNetworkService {

    @GET("data/2.5/weather?")
    suspend fun obtainUserWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("lang") language: String = "ru",
        @Query("units") units: String = "metric",
    ): Response<RemoteWeatherOnUserLocation>
}