package com.main.data.remote.models.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDetails(
    @SerialName("prefix")
    val prefix: String,

    @SerialName("suffix")
    val suffix: String,
)