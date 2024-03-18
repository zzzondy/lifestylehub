package com.feature_main.presentation.di

import com.common.network.BuildConfig
import com.feature_main.presentation.di.qualifiers.WeatherRetrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.main.data.remote.network.MainFeatureNetworkService
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

    @WeatherRetrofit
    @MainComponentScope
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.OPEN_WEATHER_API_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @MainComponentScope
    @Provides
    fun provideRecipeAddingService(@WeatherRetrofit retrofit: Retrofit): MainFeatureNetworkService =
        retrofit.create(MainFeatureNetworkService::class.java)
}