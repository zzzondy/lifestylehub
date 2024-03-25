package com.main.data.remote.network

import com.main.data.remote.models.tips.RemoteRandomTip
import retrofit2.Response
import retrofit2.http.GET

interface RandomTipNetworkService {

    @GET("activity")
    suspend fun getRandomTip(): Response<RemoteRandomTip>
}