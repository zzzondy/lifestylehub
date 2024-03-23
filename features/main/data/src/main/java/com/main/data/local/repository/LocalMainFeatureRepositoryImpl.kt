package com.main.data.local.repository

import android.content.SharedPreferences
import com.main.data.local.database.PlaceDetailsDatabase
import com.main.data.local.results.LocalObtainingPlaceDetailsResult
import com.main.data.utils.mappers.toEntity
import com.main.domain.models.places.PlaceDetails

class LocalMainFeatureRepositoryImpl(
    private val preferences: SharedPreferences,
    private val placeDetailsDatabase: PlaceDetailsDatabase,
) : LocalMainFeatureRepository {

    override suspend fun putLocationPermissionFlag(isRationaleShow: Boolean) {
        preferences.edit().apply {
            putBoolean(IS_LOCATION_EXPLANATION_DIALOG_RATIONALE_SHOW, isRationaleShow)
            apply()
        }
    }

    override suspend fun getLocationPermissionFlag(): Boolean {
        return preferences.getBoolean(IS_LOCATION_EXPLANATION_DIALOG_RATIONALE_SHOW, false)
    }

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


    companion object {
        private const val IS_LOCATION_EXPLANATION_DIALOG_RATIONALE_SHOW =
            "IS_LOCATION_EXPLANATION_DIALOG_RATIONALE_SHOW"
    }
}