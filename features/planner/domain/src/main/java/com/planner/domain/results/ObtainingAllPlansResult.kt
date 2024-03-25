package com.planner.domain.results

import com.planner.domain.models.Plan

sealed class ObtainingAllPlansResult {

    data class Success(val plans: List<Plan>) : ObtainingAllPlansResult()

    data object Error : ObtainingAllPlansResult()
}