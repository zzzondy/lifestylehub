package com.main.data.remote.network

import com.main.data.BuildConfig
import com.main.data.remote.models.places.PlacesInitialResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbyPlacesNetworkService {

    @GET("search/recommendations")
    suspend fun getNearbyPlaces(
        @Query("ll") latitudeWithLongitude: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("oauth_token") key: String = BuildConfig.FOUR_SQUARE_API_KEY,
        @Query("v") version: String = BuildConfig.FOUR_SQUARE_VERSION,
    ): Response<PlacesInitialResponse>
}