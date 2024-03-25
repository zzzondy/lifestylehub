package com.main.domain.models.results

import com.main.domain.models.tips.RandomTip

sealed class ObtainingRandomTipResult {

    data class Success(val randomTip: RandomTip) : ObtainingRandomTipResult()

    data object Error : ObtainingRandomTipResult()
}