package uz.behzoddev.mvi

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzoddev.mvi.data.repository.MviRepository
import uz.behzoddev.mvi.ui.MviAction
import uz.behzoddev.mvi.ui.MviEvent
import uz.behzoddev.mvi.ui.MviState
import uz.behzoddev.mvi.ui.MviViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MviRepository
) :
    MviViewModel<MviEvent, MviState>(initialState = MviState(), initialEvent = MviEvent.Launch) {

    fun renderEvent(event: MviEvent) {
        viewModelScope.launch {
            when (event) {
                MviEvent.ClickButton -> obtainState()
                MviEvent.Launch -> {}
            }
        }
    }

    private fun obtainState() {
        viewModelScope.launch {
            reduceState { it.copy(actions = MviAction.Loading) }
            runCatching {
                repository.fetchUsers()
            }.onSuccess { users ->
                if (users.isEmpty()) {
                    reduceState {
                        it.copy(
                            users = emptyList(),
                            actions = MviAction.Idle
                        )
                    }
                }
                if (users.isNotEmpty()) {
                    reduceState {
                        it.copy(
                            users = users,
                            actions = MviAction.Available
                        )
                    }
                    Log.d("Available state", "$users")
                }

            }.onFailure { message ->
                reduceState {
                    it.copy(
                        actions = MviAction.Failure,
                        message = message.localizedMessage
                    )
                }
            }
        }

    }
}

