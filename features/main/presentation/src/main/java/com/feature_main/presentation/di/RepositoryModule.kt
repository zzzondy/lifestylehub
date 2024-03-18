package com.feature_main.presentation.di

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.common.network.location.GetCurrentUserLocationUseCase
import com.main.data.local.repository.LocalMainFeatureRepository
import com.main.data.local.repository.LocalMainFeatureRepositoryImpl
import com.main.data.remote.network.MainFeatureNetworkService
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
        getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase,
        mainFeatureNetworkService: MainFeatureNetworkService
    ): RemoteMainFeatureRepository =
        RemoteMainFeatureRepositoryImpl(getCurrentUserLocationUseCase, mainFeatureNetworkService)

    @MainComponentScope
    @Provides
    fun provideLocalMainFeatureRepository(
        preferences: SharedPreferences
    ): LocalMainFeatureRepository = LocalMainFeatureRepositoryImpl(preferences)

    @MainComponentScope
    @Provides
    fun provideMainFeatureRepository(
        remoteMainFeatureRepository: RemoteMainFeatureRepository,
        localMainFeatureRepository: LocalMainFeatureRepository
    ): MainFeatureRepository =
        MainFeatureRepositoryImpl(remoteMainFeatureRepository, localMainFeatureRepository)
}