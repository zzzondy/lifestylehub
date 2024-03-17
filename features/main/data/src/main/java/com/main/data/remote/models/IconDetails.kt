package com.main.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IconDetails(

    @SerialName("description")
    val description: String,

    @SerialName("icon")
    val icon: String,
)