package com.auth.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomUserResponse(

    @SerialName("results")
    val results: List<RandomUser>,
)

@Serializable
data class RandomUser(

    @SerialName("name")
    val name: UserName,

    @SerialName("login")
    val login: UserLogin,

    @SerialName("phone")
    val phone: String,

    @SerialName("picture")
    val photo: UserPhoto,

    @SerialName("email")
    val email: String,
)

@Serializable
data class UserName(

    @SerialName("first")
    val first: String,

    @SerialName("last")
    val last: String,
)

@Serializable
data class UserLogin(

    @SerialName("username")
    val username: String,
)

@Serializable
data class UserPhoto(

    @SerialName("large")
    val large: String,
)