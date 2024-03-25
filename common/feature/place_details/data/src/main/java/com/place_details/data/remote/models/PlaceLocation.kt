package com.place_details.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceLocation(

    @SerialName("address")
    val address: String = "",
)
