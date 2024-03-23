package com.main.data.local.repository

interface LocalMainFeatureRepository {

    suspend fun putLocationPermissionFlag(isRationaleShow: Boolean)

    suspend fun getLocationPermissionFlag(): Boolean
}