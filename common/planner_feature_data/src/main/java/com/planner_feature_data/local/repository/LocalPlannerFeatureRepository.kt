package com.planner_feature_data.local.repository

import com.planner_feature_data.local.models.PlanEntity
import com.planner_feature_data.local.results.LocalObtainingAllPlansResult

interface LocalPlannerFeatureRepository {

    suspend fun getAllPlans(): LocalObtainingAllPlansResult

    suspend fun insertNewPlan(planEntity: PlanEntity)

    suspend fun deletePlanById(id: Long)
}