package com.auth.data.local

import android.content.SharedPreferences

class LocalAuthRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
) : LocalAuthRepository {

    override suspend fun getCurrentUserId(): Long? {
        val currentUserID = sharedPreferences.getLong(USER_ID, -1L)
        return if (currentUserID == -1L) {
            null
        } else {
            currentUserID
        }
    }

    override suspend fun setCurrentUserId(id: Long) {
        sharedPreferences.edit()
            .putLong(USER_ID, id)
            .apply()
    }

    companion object {
        private const val USER_ID = "user_id"
    }
}