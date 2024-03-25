package com.main.domain.use_cases

import com.main.domain.repository.MainFeatureRepository

class ObtainRandomTipUseCase(
    private val mainFeatureRepository: MainFeatureRepository
) {

    suspend operator fun invoke() = mainFeatureRepository.getRandomTip()
}