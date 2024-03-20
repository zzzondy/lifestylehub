package com.main.domain.models.results

import com.main.domain.models.places.PagingItem

sealed class ObtainingNearbyPlacesResult {

    data class Success(val nearbyPlaces: List<PagingItem.Place>) : ObtainingNearbyPlacesResult()

    data object Error : ObtainingNearbyPlacesResult()
}