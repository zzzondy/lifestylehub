package com.place_details.domain.models.places

data class PlaceDetails(
    val id: String,
    val name: String,
    val address: String,
    val bestPhoto: String,
    val placePhotos: List<String>,
    val categories: List<Category>,
    val phone: String? = null,
    val twitter: String? = null,
    val instagram: String? = null,
    val facebook: String? = null,
)