package com.common.ui.state_hoisting

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class StatefulViewModel<State : Any, Effect : Any, Action : Any> : ViewModel() {

    protected val _state = Channel<State>(capacity = Channel.UNLIMITED)

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    @OptIn(DelicateCoroutinesApi::class)
    protected suspend fun updateState(newState: State) {
        _state.send(newState)
    }

    protected suspend fun updateEffect(effect: Effect) {
        _effect.emit(effect)
    }

    abstract fun onAction(action: Action)
}