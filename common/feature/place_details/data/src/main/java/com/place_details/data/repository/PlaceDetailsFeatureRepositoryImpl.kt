package com.place_details.data.repository

import com.place_details.data.local.repository.LocalPlaceDetailsFeatureRepository
import com.place_details.data.local.results.LocalObtainingPlaceDetailsResult
import com.place_details.data.remote.repository.RemotePlaceDetailsFeatureRepository
import com.place_details.data.remote.result.RemoteObtainingPlaceDetailsResult
import com.place_details.data.remote.result.RemoteObtainingPlacePhotosResult
import com.place_details.data.utils.toDomain
import com.place_details.domain.models.planner.Plan
import com.place_details.domain.models.results.ObtainingPlaceDetailsResult
import com.place_details.domain.repository.PlaceDetailsFeatureRepository
import com.planner_feature_data.local.models.PlanEntity
import com.planner_feature_data.local.repository.LocalPlannerFeatureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PlaceDetailsFeatureRepositoryImpl(
    private val localPlannerFeatureRepository: LocalPlaceDetailsFeatureRepository,
    private val remotePlaceDetailsFeatureRepository: RemotePlaceDetailsFeatureRepository,
    private val plannerFeatureRepository: LocalPlannerFeatureRepository
) : PlaceDetailsFeatureRepository {

    override suspend fun getPlaceDetails(id: String): ObtainingPlaceDetailsResult {
        val localPlaceDetailsResult = localPlannerFeatureRepository.getPlaceDetails(id)
        if (localPlaceDetailsResult !is LocalObtainingPlaceDetailsResult.Success) {
            lateinit var placePhotosResult: RemoteObtainingPlacePhotosResult
            lateinit var placeDetailsResult: RemoteObtainingPlaceDetailsResult
            coroutineScope {
                launch(Dispatchers.IO) {
                    placePhotosResult = remotePlaceDetailsFeatureRepository.getPlacePhotos(id)
                }

                launch(Dispatchers.IO) {
                    placeDetailsResult = remotePlaceDetailsFeatureRepository.getPlaceDetails(id)
                }
            }

            val result = placeDetailsResult.toDomain(placePhotosResult)
            when (result) {
                is ObtainingPlaceDetailsResult.Success -> {
                    localPlannerFeatureRepository.insertPlaceDetails(result.placeDetails)
                }

                else -> {}
            }

            return result
        } else {
            return ObtainingPlaceDetailsResult.Success(
                placeDetails = localPlaceDetailsResult.placeDetails.toDomain()
            )
        }
    }

    override suspend fun insertPlan(plan: Plan) {
        plannerFeatureRepository.insertNewPlan(
            PlanEntity(
                name = plan.name,
                date = plan.date,
                notes = null,
                placeId = plan.placeId,
                placeName = plan.placeName
            )
        )
    }
}