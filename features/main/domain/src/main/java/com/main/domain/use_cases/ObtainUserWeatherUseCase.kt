package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class ObtainUserWeatherUseCase(private val mainFeatureRepository: MainFeatureRepository) {

    suspend operator fun invoke(latitude: Double, longitude: Double) =
        mainFeatureRepository.getUserWeather(latitude, longitude)
}