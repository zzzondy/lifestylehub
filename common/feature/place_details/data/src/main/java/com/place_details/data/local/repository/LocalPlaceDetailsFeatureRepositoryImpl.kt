package com.place_details.data.local.repository

import com.place_details.data.local.database.PlaceDetailsDatabase
import com.place_details.data.local.results.LocalObtainingPlaceDetailsResult
import com.place_details.data.utils.toEntity
import com.place_details.domain.models.places.PlaceDetails

class LocalPlaceDetailsFeatureRepositoryImpl(
    private val placeDetailsDatabase: PlaceDetailsDatabase,
) : LocalPlaceDetailsFeatureRepository {

    override suspend fun getPlaceDetails(id: String): LocalObtainingPlaceDetailsResult {
        return try {
            LocalObtainingPlaceDetailsResult.Success(
                placeDetailsDatabase.placeDetailsDao.getPlaceDetailsById(id)
            )
        } catch (e: NullPointerException) {
            LocalObtainingPlaceDetailsResult.NotFound
        } catch (e: Exception) {
            LocalObtainingPlaceDetailsResult.Error
        }
    }


    override suspend fun insertPlaceDetails(placeDetails: PlaceDetails) {
        placeDetailsDatabase.placeDetailsDao.insertPlaceDetails(placeDetails.toEntity())
    }
}