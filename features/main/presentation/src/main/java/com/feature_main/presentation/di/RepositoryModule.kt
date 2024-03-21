package com.feature_main.presentation.di

import android.content.SharedPreferences
import com.common.network.location.LocationManager
import com.main.data.local.database.PlaceDetailsDatabase
import com.main.data.local.repository.LocalMainFeatureRepository
import com.main.data.local.repository.LocalMainFeatureRepositoryImpl
import com.main.data.remote.network.MainFeatureNetworkService
import com.main.data.remote.network.NearbyPlacesNetworkService
import com.main.data.remote.repository.RemoteMainFeatureRepository
import com.main.data.remote.repository.RemoteMainFeatureRepositoryImpl
import com.main.data.repository.MainFeatureRepositoryImpl
import com.main.domain.repository.MainFeatureRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @MainComponentScope
    @Provides
    fun provideRemoteMainFeatureRepository(
        locationManager: LocationManager,
        mainFeatureNetworkService: MainFeatureNetworkService,
        nearbyPlacesNetworkService: NearbyPlacesNetworkService,
    ): RemoteMainFeatureRepository =
        RemoteMainFeatureRepositoryImpl(
            locationManager,
            mainFeatureNetworkService,
            nearbyPlacesNetworkService
        )

    @MainComponentScope
    @Provides
    fun provideLocalMainFeatureRepository(
        preferences: SharedPreferences,
        placesDatabase: PlaceDetailsDatabase,
    ): LocalMainFeatureRepository =
        LocalMainFeatureRepositoryImpl(preferences, placesDatabase)

    @MainComponentScope
    @Provides
    fun provideMainFeatureRepository(
        remoteMainFeatureRepository: RemoteMainFeatureRepository,
        localMainFeatureRepository: LocalMainFeatureRepository
    ): MainFeatureRepository =
        MainFeatureRepositoryImpl(remoteMainFeatureRepository, localMainFeatureRepository)
}