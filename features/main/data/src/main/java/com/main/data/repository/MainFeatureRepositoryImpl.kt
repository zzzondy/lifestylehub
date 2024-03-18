package com.main.data.repository

import com.main.data.local.repository.LocalMainFeatureRepository
import com.main.data.remote.repository.RemoteMainFeatureRepository
import com.main.data.utils.mappers.toDomain
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.repository.MainFeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainFeatureRepositoryImpl(
    private val remoteMainFeatureRepository: RemoteMainFeatureRepository,
    private val localMainFeatureRepository: LocalMainFeatureRepository,
) : MainFeatureRepository {

    override fun getUserWeather(): Flow<ObtainingUserWeatherResult> =
        remoteMainFeatureRepository.getUserWeather().map { it.toDomain() }

    override suspend fun putLocationPermissionFlag(isRationaleShow: Boolean) {
        localMainFeatureRepository.putLocationPermissionFlag(isRationaleShow)
    }

    override suspend fun getLocationPermissionFlag(): Boolean =
        localMainFeatureRepository.getLocationPermissionFlag()
}