package com.auth.data.utils

import com.auth.data.authorization.entities.UserEntity
import com.auth.data.remote.models.RandomUser
import com.auth.domain.models.User

fun UserEntity.toDomain(): User = User(
    id = this.id,
    name = this.name,
    surname = this.surname,
    login = this.login,
    phone = this.phone,
    photo = this.photo,
    email = this.email,
)

fun RandomUser.toEntity(password: String) = UserEntity(
    name = this.name.first,
    surname = this.name.last,
    login = this.login.username,
    email = this.email,
    photo = this.photo.large,
    phone = this.phone,
    password = password
)