package com.main.data.local.results

import com.main.data.local.entities.PlaceDetailsEntity

sealed class LocalObtainingPlaceDetailsResult {

    data class Success(val placeDetails: PlaceDetailsEntity) : LocalObtainingPlaceDetailsResult()

    data object NotFound : LocalObtainingPlaceDetailsResult()

    data object Error : LocalObtainingPlaceDetailsResult()
}