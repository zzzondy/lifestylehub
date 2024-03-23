package com.planner.domain.use_cases

import com.planner.domain.repository.PlannerFeatureRepository

class DeletePlanByIdUseCase(
    private val plannerFeatureRepository: PlannerFeatureRepository
) {

    suspend operator fun invoke(id: Long) = plannerFeatureRepository.deletePlanById(id)
}