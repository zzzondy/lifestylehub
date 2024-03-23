package com.planner.data.utils.extensions

import com.planner.domain.models.Plan
import com.planner.domain.results.ObtainingAllPlansResult
import com.planner_feature_data.local.models.PlanEntity
import com.planner_feature_data.local.results.LocalObtainingAllPlansResult

fun LocalObtainingAllPlansResult.toDomain() = when (this) {
    is LocalObtainingAllPlansResult.Success -> {
        ObtainingAllPlansResult.Success(this.plans.map { it.toDomain() })
    }

    is LocalObtainingAllPlansResult.Error -> {
        ObtainingAllPlansResult.Error
    }
}

private fun PlanEntity.toDomain() = Plan(
    id = this.id,
    name = this.name,
    notes = this.notes,
    placeId = this.placeId,
    placeName = this.placeName,
    date = this.date
)

fun Plan.toEntity() = PlanEntity(
    name = this.name,
    notes = this.notes,
    placeId = this.placeId,
    placeName = this.placeName,
    date = this.date
)