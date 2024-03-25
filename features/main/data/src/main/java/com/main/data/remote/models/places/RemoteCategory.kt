package com.main.data.remote.models.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCategory(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("icon")
    val iconDetails: IconDetails,
)

@Serializable
data class IconDetails(
    @SerialName("prefix")
    val prefix: String,

    @SerialName("suffix")
    val suffix: String,
)
