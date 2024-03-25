package com.auth.data.remote.repository

import com.auth.data.remote.results.RemoteObtainingRandomUserResult

interface RemoteAuthRepository {

    suspend fun getRandomUser(): RemoteObtainingRandomUserResult
}