package com.main.data.remote.models.places

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlacesInitialResponse(
    @SerialName("response")
    val response: PlacesGroupResponse
)

@Serializable
data class PlacesGroupResponse(
    @SerialName("group")
    val group: PlacesResponse
)

@Serializable
data class PlacesResponse(
    @SerialName("results")
    val results: List<PlaceResponse> = emptyList(),
)

@Serializable
data class PlaceResponse(

    @SerialName("venue")
    val venue: Venue,

    @SerialName("photo")
    val photo: PhotoDetails = PhotoDetails(
        "",
        ""
    ),
)

@Serializable
data class Venue(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("categories")
    val categories: List<RemoteCategory>,

    @SerialName("location")
    val location: PlaceLocation,
)