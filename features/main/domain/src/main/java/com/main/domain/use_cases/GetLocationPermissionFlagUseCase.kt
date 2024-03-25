package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class GetLocationPermissionFlagUseCase(
    private val mainFeatureRepository: MainFeatureRepository
) {

    suspend operator fun invoke() = mainFeatureRepository.getLocationPermissionFlag()
}