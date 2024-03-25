package com.auth.data.remote.network

import com.auth.data.remote.models.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthNetworkService {

    @GET("api")
    suspend fun getRandomUser(@Query("format") format: String = "json"): Response<RandomUserResponse>
}