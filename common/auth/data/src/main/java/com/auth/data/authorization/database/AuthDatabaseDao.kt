package com.auth.data.authorization.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.auth.data.authorization.AuthDatabaseContract
import com.auth.data.authorization.entities.UserEntity

@Dao
interface AuthDatabaseDao {

    @Query("SELECT * FROM ${AuthDatabaseContract.TABLE_NAME} WHERE ${AuthDatabaseContract.USER_ID} = :id")
    fun getUserById(id: Long): UserEntity

    @Insert
    fun createUser(userEntity: UserEntity): Long

    @Query("SELECT * FROM ${AuthDatabaseContract.TABLE_NAME} WHERE ${AuthDatabaseContract.USER_LOGIN} = :login")
    fun getUserByLogin(login: String): UserEntity?
}