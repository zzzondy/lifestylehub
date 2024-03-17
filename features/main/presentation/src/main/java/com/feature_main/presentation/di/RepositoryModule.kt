package com.feature_main.presentation.di

import com.common.network.location.GetCurrentUserLocationUseCase
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
    fun provideMainFeatureRepository(remoteMainFeatureRepository: RemoteMainFeatureRepository): MainFeatureRepository =
        MainFeatureRepositoryImpl(remoteMainFeatureRepository)
}