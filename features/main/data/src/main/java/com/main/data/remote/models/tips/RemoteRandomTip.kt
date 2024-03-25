package com.main.data.remote.models.tips

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRandomTip(

    @SerialName("activity")
    val name: String
)