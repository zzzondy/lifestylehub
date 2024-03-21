package com.main.data.remote.models.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceContact(
    @SerialName("formattedPhone")
    val phone: String? = null,

    @SerialName("instagram")
    val instagram: String? = null,

    @SerialName("facebookUsername")
    val facebook: String? = null,

    @SerialName("twitter")
    val twitter: String? = null,
)