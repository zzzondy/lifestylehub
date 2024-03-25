package com.auth.data.remote.results

import com.auth.data.remote.models.RandomUser

sealed class RemoteObtainingRandomUserResult {

    data class Success(val randomUser: RandomUser) : RemoteObtainingRandomUserResult()

    data object Error : RemoteObtainingRandomUserResult()
}