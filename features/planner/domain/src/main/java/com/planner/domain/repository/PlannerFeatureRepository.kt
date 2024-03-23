package com.planner.domain.repository

import com.planner.domain.models.Plan
import com.planner.domain.results.ObtainingAllPlansResult

interface PlannerFeatureRepository {

    suspend fun getAllPlans(): ObtainingAllPlansResult

    suspend fun addPlan(plan: Plan)

    suspend fun deletePlanById(id: Long)
}