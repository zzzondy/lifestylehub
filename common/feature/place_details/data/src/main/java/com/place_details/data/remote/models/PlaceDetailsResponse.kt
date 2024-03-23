package com.place_details.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDetailsResponse(

    @SerialName("response")
    val response: RemotePlaceDetails
)

@Serializable
data class RemotePlaceDetails(

    @SerialName("venue")
    val venue: PlaceDetailsVenue
)

@Serializable
data class PlaceDetailsVenue(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("location")
    val location: PlaceLocation,

    @SerialName("contact")
    val contact: PlaceContact,

    @SerialName("bestPhoto")
    val bestPhoto: PlaceBestPhoto,

    @SerialName("categories")
    val categories: List<RemoteCategory>
)