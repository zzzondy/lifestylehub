package com.place_details.domain.models.planner

data class Plan(
    val id: Long = 0,
    val name: String,
    val date: String,
    val placeId: String?,
    val placeName: String?,
)
