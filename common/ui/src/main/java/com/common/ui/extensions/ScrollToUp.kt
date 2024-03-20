package com.common.ui.extensions

import androidx.compose.foundation.lazy.LazyListState

suspend fun LazyListState.animateScrollToUp() {
    this.animateScrollToItem(0)
}