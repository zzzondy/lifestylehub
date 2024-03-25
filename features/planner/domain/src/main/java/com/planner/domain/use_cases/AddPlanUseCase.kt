package com.planner.domain.use_cases

import com.planner.domain.models.Plan
import com.planner.domain.repository.PlannerFeatureRepository

class AddPlanUseCase(private val plannerFeatureRepository: PlannerFeatureRepository) {

    suspend operator fun invoke(plan: Plan) = plannerFeatureRepository.addPlan(plan)
}