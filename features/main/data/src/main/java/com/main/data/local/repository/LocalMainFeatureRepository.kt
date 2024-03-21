package com.main.data.local.repository

import com.main.data.local.results.LocalObtainingPlaceDetailsResult
import com.main.domain.models.places.PlaceDetails

interface LocalMainFeatureRepository {

    suspend fun putLocationPermissionFlag(isRationaleShow: Boolean)

    suspend fun getLocationPermissionFlag(): Boolean

    suspend fun getPlaceDetails(id: String): LocalObtainingPlaceDetailsResult

    suspend fun insertPlaceDetails(placeDetails: PlaceDetails)
}