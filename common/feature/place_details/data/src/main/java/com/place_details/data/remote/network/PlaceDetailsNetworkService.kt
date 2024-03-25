package com.place_details.data.remote.network

import com.place_details.data.BuildConfig
import com.place_details.data.remote.models.PhotosResponse
import com.place_details.data.remote.models.PlaceDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceDetailsNetworkService {

    @GET("venues/{id}")
    suspend fun getPlaceDetails(
        @Path("id") id: String,
        @Query("oauth_token") key: String = BuildConfig.FOUR_SQUARE_API_KEY,
        @Query("v") version: String = BuildConfig.FOUR_SQUARE_VERSION,
    ): Response<PlaceDetailsResponse>

    @GET("venues/{id}/photos")
    suspend fun getPlacePhotos(
        @Path("id") id: String,
        @Query("oauth_token") key: String = BuildConfig.FOUR_SQUARE_API_KEY,
        @Query("v") version: String = BuildConfig.FOUR_SQUARE_VERSION,
    ): Response<PhotosResponse>
}