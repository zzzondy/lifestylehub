package com.main.data.remote.models.results

import com.main.data.remote.models.tips.RemoteRandomTip

sealed class RemoteObtainingRandomTipResult {

    data class Success(val remoteRandomTip: RemoteRandomTip) : RemoteObtainingRandomTipResult()

    data object Error : RemoteObtainingRandomTipResult()
}