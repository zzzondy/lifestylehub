package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class UpdateLocationPermissionFlagUseCase(
    private val mainFeatureRepository: MainFeatureRepository
) {

    suspend operator fun invoke(isRationaleShow: Boolean) {
        mainFeatureRepository.putLocationPermissionFlag(isRationaleShow)
    }
}