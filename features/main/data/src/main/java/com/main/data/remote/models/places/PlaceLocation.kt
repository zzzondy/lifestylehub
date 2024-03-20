package com.main.data.remote.models.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceLocation(

    @SerialName("address")
    val address: String = "",
)
