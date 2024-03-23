package com.place_details.domain.use_cases

import com.place_details.domain.models.planner.Plan
import com.place_details.domain.repository.PlaceDetailsFeatureRepository

class AddNewPlanUseCase(
    private val placeDetailsFeatureRepository: PlaceDetailsFeatureRepository
) {

    suspend operator fun invoke(plan: Plan) = placeDetailsFeatureRepository.insertPlan(plan)
}