package com.main.data.local.repository

import android.content.SharedPreferences

class LocalMainFeatureRepositoryImpl(
    private val preferences: SharedPreferences,
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

    companion object {
        private const val IS_LOCATION_EXPLANATION_DIALOG_RATIONALE_SHOW =
            "IS_LOCATION_EXPLANATION_DIALOG_RATIONALE_SHOW"
    }
}