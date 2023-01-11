package uz.behzoddev.mvi_orbit.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.behzoddev.mvi_orbit.data.repository.MviOrbitRepository
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MviOrbitViewModel @Inject constructor(
    private val repository: MviOrbitRepository
) : ContainerHost<MviOrbitState, MviOrbitSideEffect>, ViewModel() {

    override val container: Container<MviOrbitState, MviOrbitSideEffect> =
        container(initialState = MviOrbitState())


    fun clickFetchUser() = intent {
        postSideEffect(MviOrbitSideEffect.Click("Button is clicked"))
        obtainState()
    }

    private fun obtainState() = intent {
        runCatching {
            repository.fetchUsers()
        }.onSuccess { responseUsers ->
            reduce {
                state.copy(
                    users = responseUsers
                )
            }
            postSideEffect(MviOrbitSideEffect.Hide)
        }.onFailure {
            postSideEffect(MviOrbitSideEffect.Toast(it.localizedMessage))
        }

    }
}