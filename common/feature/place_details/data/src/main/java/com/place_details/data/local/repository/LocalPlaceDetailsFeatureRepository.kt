package com.place_details.data.local.repository

import com.place_details.data.local.results.LocalObtainingPlaceDetailsResult
import com.place_details.domain.models.places.PlaceDetails

interface LocalPlaceDetailsFeatureRepository {

    suspend fun getPlaceDetails(id: String): LocalObtainingPlaceDetailsResult

    suspend fun insertPlaceDetails(placeDetails: PlaceDetails)
}