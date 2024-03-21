package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class GetPlaceDetailsUseCase(private val mainFeatureRepository: MainFeatureRepository) {

    suspend operator fun invoke(id: String) = mainFeatureRepository.getPlaceDetails(id)
}