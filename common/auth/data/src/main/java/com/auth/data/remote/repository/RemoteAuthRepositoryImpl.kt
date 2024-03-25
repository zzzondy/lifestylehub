package com.auth.data.remote.repository

import android.util.Log
import com.auth.data.remote.network.AuthNetworkService
import com.auth.data.remote.results.RemoteObtainingRandomUserResult

class RemoteAuthRepositoryImpl(
    private val authNetworkService: AuthNetworkService
) : RemoteAuthRepository {

    override suspend fun getRandomUser(): RemoteObtainingRandomUserResult {
        return try {
            val result = authNetworkService.getRandomUser()
            if (result.isSuccessful && result.body() != null) {
                RemoteObtainingRandomUserResult.Success(result.body()!!.results[0])
            } else {
                RemoteObtainingRandomUserResult.Error
            }
        } catch (e: Exception) {
            Log.d("Auth", e.toString())
            RemoteObtainingRandomUserResult.Error
        }
    }
}