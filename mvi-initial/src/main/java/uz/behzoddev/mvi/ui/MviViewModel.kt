package uz.behzoddev.mvi.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import uz.behzoddev.mvi.core.Event
import uz.behzoddev.mvi.core.State

abstract class MviViewModel<E : Event, S : State>(initialEvent: E, initialState: S) : ViewModel() {

    private var _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private var _uiEvent: MutableStateFlow<E> = MutableStateFlow(initialEvent)
    val uiEvent = _uiEvent.asStateFlow()

    protected fun reduceState(
        block: (S) -> S
    ): S {
        return _uiState.updateAndGet(block)
    }

    protected fun reduceEvent(
        block: (E) -> E
    ): E {
        return _uiEvent.updateAndGet(block)
    }
}