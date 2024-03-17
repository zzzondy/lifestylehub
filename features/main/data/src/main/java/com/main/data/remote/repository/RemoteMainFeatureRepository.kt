package com.main.data.remote.repository

import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import kotlinx.coroutines.flow.Flow

interface RemoteMainFeatureRepository {

    fun getUserWeather(): Flow<RemoteObtainingUserWeatherResult>
}