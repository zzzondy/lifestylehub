package com.planner_feature_data.local.repository

import com.planner_feature_data.local.database.PlannerFeatureDatabase
import com.planner_feature_data.local.models.PlanEntity
import com.planner_feature_data.local.results.LocalObtainingAllPlansResult

class LocalPlannerFeatureRepositoryImpl(
    private val plannerFeatureDatabase: PlannerFeatureDatabase,
) : LocalPlannerFeatureRepository {

    override suspend fun getAllPlans(): LocalObtainingAllPlansResult {
        return try {
            LocalObtainingAllPlansResult.Success(
                plannerFeatureDatabase.plannerFeatureDao.getAllPlans()
            )
        } catch (e: Exception) {
            LocalObtainingAllPlansResult.Error
        }
    }

    override suspend fun insertNewPlan(planEntity: PlanEntity) {
        plannerFeatureDatabase.plannerFeatureDao.insertNewPlan(planEntity)
    }

    override suspend fun deletePlanById(id: Long) {
        plannerFeatureDatabase.plannerFeatureDao.deleteById(id)
    }
}