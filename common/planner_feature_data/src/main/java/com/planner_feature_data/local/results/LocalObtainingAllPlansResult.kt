package com.planner_feature_data.local.results

import com.planner_feature_data.local.models.PlanEntity

sealed class LocalObtainingAllPlansResult {

    data class Success(val plans: List<PlanEntity>) : LocalObtainingAllPlansResult()

    data object Error : LocalObtainingAllPlansResult()
}