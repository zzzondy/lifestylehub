package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class GetPagedNearbyPlacesUseCase(
    private val mainFeatureRepository: MainFeatureRepository,
) {

    suspend operator fun invoke(latitude: Double, longitude: Double, limit: Int, offset: Int) =
        mainFeatureRepository.getNearbyPlaces(latitude, longitude, limit, offset)
}