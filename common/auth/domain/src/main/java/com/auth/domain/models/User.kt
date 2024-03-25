package com.auth.domain.models

data class User(
    val id: Long = 0,
    val name: String,
    val surname: String,
    val login: String,
    val phone: String,
    val photo: String,
    val email: String,
)
