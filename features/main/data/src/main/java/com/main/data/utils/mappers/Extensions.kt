package com.main.data.utils.mappers

import com.common.network.location.MapLocation
import com.main.data.remote.models.places.PlaceResponse
import com.main.data.remote.models.places.RemoteCategory
import com.main.data.remote.models.results.RemoteObtainingNearbyPlacesResult
import com.main.data.remote.models.results.RemoteObtainingRandomTipResult
import com.main.data.remote.models.results.RemoteObtainingUserWeatherResult
import com.main.data.remote.models.tips.RemoteRandomTip
import com.main.data.remote.models.weather.RemoteWeatherOnUserLocation
import com.main.domain.models.UserLocation
import com.main.domain.models.places.Category
import com.main.domain.models.places.PagingItem
import com.main.domain.models.results.ObtainingNearbyPlacesResult
import com.main.domain.models.results.ObtainingRandomTipResult
import com.main.domain.models.results.ObtainingUserWeatherResult
import com.main.domain.models.tips.RandomTip
import com.main.domain.models.weather.WeatherOnUserLocation

fun RemoteObtainingUserWeatherResult.toDomain() = when (this) {
    is RemoteObtainingUserWeatherResult.Success -> ObtainingUserWeatherResult.Success(this.remoteWeatherOnUserLocation.toDomain())

    is RemoteObtainingUserWeatherResult.NetworkError -> ObtainingUserWeatherResult.NetworkError
}

fun RemoteObtainingNearbyPlacesResult.toDomain() = when (this) {
    is RemoteObtainingNearbyPlacesResult.Success -> ObtainingNearbyPlacesResult.Success(this.remotePlaces.map { it.toDomain() })

    is RemoteObtainingNearbyPlacesResult.Error -> ObtainingNearbyPlacesResult.Error
}

fun RemoteObtainingRandomTipResult.toDomain() = when (this) {
    is RemoteObtainingRandomTipResult.Success -> ObtainingRandomTipResult.Success(this.remoteRandomTip.toDomain())

    is RemoteObtainingRandomTipResult.Error -> ObtainingRandomTipResult.Error
}

fun RemoteRandomTip.toDomain() = RandomTip(
    name
)

fun RemoteWeatherOnUserLocation.toDomain() =
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

fun RemoteCategory.toDomain(): Category = Category(
    id = this.id,
    name = this.name,
    icon = iconDetails.prefix + "120" + iconDetails.suffix,
)