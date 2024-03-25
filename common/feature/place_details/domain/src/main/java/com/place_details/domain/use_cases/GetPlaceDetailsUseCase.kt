package com.place_details.domain.use_cases

import com.place_details.domain.repository.PlaceDetailsFeatureRepository

class GetPlaceDetailsUseCase(private val placeDetailsFeatureRepository: PlaceDetailsFeatureRepository) {

    suspend operator fun invoke(id: String) = placeDetailsFeatureRepository.getPlaceDetails(id)
}