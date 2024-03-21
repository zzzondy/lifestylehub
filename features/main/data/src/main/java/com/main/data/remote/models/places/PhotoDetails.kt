package com.main.data.remote.models.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotosResponse(
    @SerialName("response")
    val response: PhotosMiddlewareResponse
)

@Serializable
data class PhotosMiddlewareResponse(
    @SerialName("photos")
    val photos: PhotoResponseItems
)

@Serializable
data class PhotoResponseItems(
    @SerialName("items")
    val items: List<PhotoDetails>
)

@Serializable
data class PhotoDetails(
    @SerialName("prefix")
    val prefix: String,

    @SerialName("suffix")
    val suffix: String,
)