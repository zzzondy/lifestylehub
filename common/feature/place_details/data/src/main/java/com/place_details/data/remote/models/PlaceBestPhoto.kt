package com.place_details.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceBestPhoto(
    @SerialName("prefix")
    val prefix: String,

    @SerialName("suffix")
    val suffix: String,
)
