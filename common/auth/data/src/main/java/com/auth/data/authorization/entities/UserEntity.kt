package com.auth.data.authorization.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.auth.data.authorization.AuthDatabaseContract

@Entity(
    tableName = AuthDatabaseContract.TABLE_NAME,
    indices = [Index(AuthDatabaseContract.USER_ID)]
)
data class UserEntity(

    @ColumnInfo(AuthDatabaseContract.USER_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(AuthDatabaseContract.USER_NAME)
    val name: String,

    @ColumnInfo(AuthDatabaseContract.USER_SURNAME)
    val surname: String,

    @ColumnInfo(AuthDatabaseContract.USER_LOGIN)
    val login: String,

    @ColumnInfo(AuthDatabaseContract.USER_PHONE)
    val phone: String,

    @ColumnInfo(AuthDatabaseContract.USER_PHOTO)
    val photo: String,

    @ColumnInfo(AuthDatabaseContract.USER_EMAIL)
    val email: String,

    @ColumnInfo(AuthDatabaseContract.USER_PASSWORD)
    val password: String,
)


