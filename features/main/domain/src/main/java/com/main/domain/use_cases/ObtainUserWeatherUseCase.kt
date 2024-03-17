package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class ObtainUserWeatherUseCase(private val mainFeatureRepository: MainFeatureRepository) {

    operator fun invoke() = mainFeatureRepository.getUserWeather()
}