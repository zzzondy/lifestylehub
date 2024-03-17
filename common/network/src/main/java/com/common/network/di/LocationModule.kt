package com.common.network.di

import android.content.Context
import com.common.network.location.GetCurrentUserLocationUseCase
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Singleton
    @Provides
    fun provideGetCurrentUserLocationUseCase(context: Context): GetCurrentUserLocationUseCase =
        GetCurrentUserLocationUseCase(
            LocationServices.getFusedLocationProviderClient(context),
            context
        )
}