package com.place_details.data.utils

import com.place_details.data.local.entities.PlaceDetailsEntity
import com.place_details.data.remote.models.PhotoDetails
import com.place_details.data.remote.models.PlaceDetailsVenue
import com.place_details.data.remote.models.RemoteCategory
import com.place_details.data.remote.result.RemoteObtainingPlaceDetailsResult
import com.place_details.data.remote.result.RemoteObtainingPlacePhotosResult
import com.place_details.domain.models.places.Category
import com.place_details.domain.models.places.PlaceDetails
import com.place_details.domain.models.results.ObtainingPlaceDetailsResult

fun PlaceDetails.toEntity() = PlaceDetailsEntity(
    id, name, address, bestPhoto, placePhotos, categories, phone, twitter, facebook, instagram
)

fun PlaceDetailsEntity.toDomain() = PlaceDetails(
    id, name, address, bestPhoto, placePhotos, categories, phone, twitter, instagram, facebook
)

fun RemoteObtainingPlaceDetailsResult.toDomain(placePhotos: RemoteObtainingPlacePhotosResult) =
    when (placePhotos) {
        is RemoteObtainingPlacePhotosResult.Success -> {
            when (this) {
                is RemoteObtainingPlaceDetailsResult.Success -> ObtainingPlaceDetailsResult.Success(
                    this.placeDetails.toDomain(placePhotos.photos)
                )

                is RemoteObtainingPlaceDetailsResult.Error -> ObtainingPlaceDetailsResult.Error
            }
        }

        is RemoteObtainingPlacePhotosResult.Error -> ObtainingPlaceDetailsResult.Error
    }

private fun PlaceDetailsVenue.toDomain(placePhotos: List<PhotoDetails>): PlaceDetails =
    PlaceDetails(
        id = this.id,
        name = this.name,
        address = this.location.address,
        bestPhoto = this.bestPhoto.prefix + "original" + this.bestPhoto.suffix,
        phone = this.contact.phone,
        instagram = this.contact.instagram,
        twitter = this.contact.twitter,
        facebook = this.contact.facebook,
        categories = this.categories.map { it.toDomain() },
        placePhotos = placePhotos.map { it.prefix + "original" + it.suffix }
    )

private fun RemoteCategory.toDomain(): Category = Category(
    id = this.id,
    name = this.name,
    icon = iconDetails.prefix + "120" + iconDetails.suffix,
)
