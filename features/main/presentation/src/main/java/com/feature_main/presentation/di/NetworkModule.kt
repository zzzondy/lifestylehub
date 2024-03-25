package com.feature_main.presentation.di

import com.common.network.BuildConfig
import com.feature_main.presentation.di.qualifiers.PlacesRetrofit
import com.feature_main.presentation.di.qualifiers.RandomTipsRetrofit
import com.feature_main.presentation.di.qualifiers.WeatherRetrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.main.data.remote.network.MainFeatureNetworkService
import com.main.data.remote.network.NearbyPlacesNetworkService
import com.main.data.remote.network.RandomTipNetworkService
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
    fun provideWeatherRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.OPEN_WEATHER_API_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @PlacesRetrofit
    @MainComponentScope
    @Provides
    fun providePlacesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.FOUR_SQUARE_API_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @RandomTipsRetrofit
    @MainComponentScope
    @Provides
    fun provideRandomTipsRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BORED_API_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @MainComponentScope
    @Provides
    fun provideRandomTipsNetworkService(@RandomTipsRetrofit retrofit: Retrofit): RandomTipNetworkService =
        retrofit.create(RandomTipNetworkService::class.java)

    @MainComponentScope
    @Provides
    fun provideMainFeatureNetworkService(@WeatherRetrofit retrofit: Retrofit): MainFeatureNetworkService =
        retrofit.create(MainFeatureNetworkService::class.java)

    @MainComponentScope
    @Provides
    fun providePlacesNetworkServiceService(@PlacesRetrofit retrofit: Retrofit): NearbyPlacesNetworkService =
        retrofit.create(NearbyPlacesNetworkService::class.java)
}