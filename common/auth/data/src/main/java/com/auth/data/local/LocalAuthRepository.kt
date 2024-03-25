package com.auth.data.local

interface LocalAuthRepository {

    suspend fun getCurrentUserId(): Long?

    suspend fun setCurrentUserId(id: Long)
}