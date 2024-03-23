package com.place_details.presentation.place_details.di

import com.place_details.data.local.database.PlaceDetailsDatabase
import com.place_details.data.local.repository.LocalPlaceDetailsFeatureRepository
import com.place_details.data.local.repository.LocalPlaceDetailsFeatureRepositoryImpl
import com.place_details.data.remote.network.PlaceDetailsNetworkService
import com.place_details.data.remote.repository.RemotePlaceDetailsFeatureRepository
import com.place_details.data.remote.repository.RemotePlaceDetailsFeatureRepositoryImpl
import com.place_details.data.repository.PlaceDetailsFeatureRepositoryImpl
import com.place_details.domain.repository.PlaceDetailsFeatureRepository
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @PlaceDetailsFeatureScope
    @Provides
    fun provideRemotePlaceDetailsFeatureRepository(
        placeDetailsNetworkService: PlaceDetailsNetworkService
    ): RemotePlaceDetailsFeatureRepository = RemotePlaceDetailsFeatureRepositoryImpl(
        placeDetailsNetworkService
    )

    @PlaceDetailsFeatureScope
    @Provides
    fun provideLocalPlaceDetailsFeatureRepository(
        placeDetailsDatabase: PlaceDetailsDatabase
    ): LocalPlaceDetailsFeatureRepository =
        LocalPlaceDetailsFeatureRepositoryImpl(
            placeDetailsDatabase
        )

    @PlaceDetailsFeatureScope
    @Provides
    fun providePlaceDetailsFeatureRepository(
        localPlaceDetailsFeatureRepository: LocalPlaceDetailsFeatureRepository,
        remotePlaceDetailsFeatureRepository: RemotePlaceDetailsFeatureRepository,
        plannerFeatureRepository: LocalPlannerFeatureRepository
    ): PlaceDetailsFeatureRepository =
        PlaceDetailsFeatureRepositoryImpl(
            localPlaceDetailsFeatureRepository,
            remotePlaceDetailsFeatureRepository,
            plannerFeatureRepository
        )
}