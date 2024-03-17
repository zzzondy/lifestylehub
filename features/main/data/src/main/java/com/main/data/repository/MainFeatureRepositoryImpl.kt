package com.main.data.repository

import com.main.data.remote.repository.RemoteMainFeatureRepository
import com.main.data.utils.mappers.toDomain
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.repository.MainFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainFeatureRepositoryImpl(
    private val remoteMainFeatureRepository: RemoteMainFeatureRepository
) : MainFeatureRepository {

    override fun getUserWeather(): Flow<ObtainingUserWeatherResult> =
        remoteMainFeatureRepository.getUserWeather().map { it.toDomain() }
}