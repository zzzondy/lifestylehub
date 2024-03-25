package com.planner.domain.use_cases

import com.planner.domain.repository.PlannerFeatureRepository

class GetAllPlansUseCase(
    private val plannerFeatureRepository: PlannerFeatureRepository
) {
    
    suspend operator fun invoke() = plannerFeatureRepository.getAllPlans()
}