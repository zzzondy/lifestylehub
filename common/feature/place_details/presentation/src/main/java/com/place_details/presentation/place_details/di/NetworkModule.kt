package com.place_details.presentation.place_details.di

import com.common.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.place_details.data.remote.network.PlaceDetailsNetworkService
import com.place_details.presentation.place_details.di.qualifiers.PlaceDetailsRetrofit
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @PlaceDetailsRetrofit
    @PlaceDetailsFeatureScope
    @Provides
    fun providePlaceDetailsRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.FOUR_SQUARE_API_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @PlaceDetailsFeatureScope
    @Provides
    fun providePlaceDetailsNetworkServiceService(@PlaceDetailsRetrofit retrofit: Retrofit): PlaceDetailsNetworkService =
        retrofit.create(PlaceDetailsNetworkService::class.java)
}