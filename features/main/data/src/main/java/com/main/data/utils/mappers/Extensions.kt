package com.main.data.utils.mappers

import com.common.network.location.MapLocation
import com.main.data.local.entities.PlaceDetailsEntity
import com.main.data.remote.models.places.PhotoDetails
import com.main.data.remote.models.places.PlaceDetailsVenue
import com.main.data.remote.models.places.PlaceResponse
import com.main.data.remote.models.places.RemoteCategory
import com.main.data.remote.models.results.RemoteObtainingNearbyPlacesResult
import com.main.data.remote.models.results.RemoteObtainingPlaceDetailsResult
import com.main.data.remote.models.results.RemoteObtainingPlacePhotosResult
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation
import com.main.domain.models.UserLocation
import com.main.domain.models.places.Category
import com.main.domain.models.places.PagingItem
import com.main.domain.models.places.PlaceDetails
import com.main.domain.models.results.ObtainingNearbyPlacesResult
import com.main.domain.models.results.ObtainingPlaceDetailsResult
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.models.weather.WeatherOnUserLocation

fun RemoteObtainingUserWeatherResult.toDomain() = when (this) {
    is RemoteObtainingUserWeatherResult.Success -> ObtainingUserWeatherResult.Success(this.remoteWeatherOnUserLocation.toDomain())

    is RemoteObtainingUserWeatherResult.NetworkError -> ObtainingUserWeatherResult.NetworkError
}

fun RemoteObtainingNearbyPlacesResult.toDomain() = when (this) {
    is RemoteObtainingNearbyPlacesResult.Success -> ObtainingNearbyPlacesResult.Success(this.remotePlaces.map { it.toDomain() })

    is RemoteObtainingNearbyPlacesResult.Error -> ObtainingNearbyPlacesResult.Error
}

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

fun PlaceDetailsEntity.toDomain() = PlaceDetails(
    id, name, address, bestPhoto, placePhotos, categories, phone, twitter, instagram, facebook
)

fun PlaceDetails.toEntity() = PlaceDetailsEntity(
    id, name, address, bestPhoto, placePhotos, categories, phone, twitter, facebook, instagram
)

private fun RemoteWeatherOnUserLocation.toDomain() =
    WeatherOnUserLocation(
        temperature = this.mainWeatherData.temperature,
        icon = this.iconDetails[0].icon,
        minTemperature = this.mainWeatherData.minTemperature,
        maxTemperature = this.mainWeatherData.maxTemperature,
        temperatureFeelsLike = this.mainWeatherData.feelingTemperature,
        weatherCondition = this.iconDetails[0].description,
        currentCity = this.currentCity
    )

fun MapLocation.toDomain(): UserLocation = UserLocation(latitude, longitude)

fun PlaceResponse.toDomain(): PagingItem.Place =
    PagingItem.Place(
        id = this.venue.id,
        name = this.venue.name,
        address = this.venue.location.address,
        photo = this.photo.prefix + "original" + this.photo.suffix,
        categories = this.venue.categories.map { it.toDomain() },
    )

private fun RemoteCategory.toDomain(): Category = Category(
    id = this.id,
    name = this.name,
    icon = iconDetails.prefix + "120" + iconDetails.suffix,
)

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