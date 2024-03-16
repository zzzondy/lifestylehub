package com.lifestylehub.features.main.screens.main.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lifestylehub.BuildConfig
import com.lifestylehub.features.main.screens.main.di.network_qualifiers.WeatherRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class WeatherNetworkModule {

    @WeatherRetrofit
    @ViewModelScoped
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.OPEN_WEATHER_API_URL)
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
}