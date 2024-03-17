package com.main.domain.repository

import com.main.domain.models.results.ObtainingUserWeatherResult
import kotlinx.coroutines.flow.Flow

interface MainFeatureRepository {

    fun getUserWeather(): Flow<ObtainingUserWeatherResult>
}