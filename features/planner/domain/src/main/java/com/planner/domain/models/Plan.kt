package com.planner.domain.models

data class Plan(
    val id: Long = 0,
    val name: String,
    val date: String,
    val notes: String?,
    val placeId: String?,
    val placeName: String?,
)
