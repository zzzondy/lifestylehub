package com.common.ui.state_hoisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class StatefulViewModel<State : Any, Effect : Any, Action : Any> : ViewModel() {

    private val _state = Channel<State>()
    val state = _state.consumeAsFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    protected fun updateState(newState: State) {
        viewModelScope.launch {
            _state.send(newState)
        }
    }

    protected fun updateEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    abstract fun onAction(action: Action)
}