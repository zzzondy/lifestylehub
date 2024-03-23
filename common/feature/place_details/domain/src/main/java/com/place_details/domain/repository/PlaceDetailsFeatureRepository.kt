package com.place_details.domain.repository

import com.place_details.domain.models.results.ObtainingPlaceDetailsResult

interface PlaceDetailsFeatureRepository {

    suspend fun getPlaceDetails(id: String): ObtainingPlaceDetailsResult
}