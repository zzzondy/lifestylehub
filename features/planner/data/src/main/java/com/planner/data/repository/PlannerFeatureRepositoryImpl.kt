package com.planner.data.repository

import com.planner.data.utils.extensions.toDomain
import com.planner.data.utils.extensions.toEntity
import com.planner.domain.models.Plan
import com.planner.domain.repository.PlannerFeatureRepository
import com.planner.domain.results.ObtainingAllPlansResult
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepository

class PlannerFeatureRepositoryImpl(
    private val localPlannerFeatureRepository: LocalPlannerFeatureRepository
) : PlannerFeatureRepository {

    override suspend fun getAllPlans(): ObtainingAllPlansResult =
        localPlannerFeatureRepository.getAllPlans().toDomain()

    override suspend fun addPlan(plan: Plan) {
        localPlannerFeatureRepository.insertNewPlan(plan.toEntity())
    }

    override suspend fun deletePlanById(id: Long) {
        localPlannerFeatureRepository.deletePlanById(id)
    }
}