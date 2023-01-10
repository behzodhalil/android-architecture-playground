package uz.behzoddev.mvi.ui

import uz.behzoddev.mvi.core.State
import uz.behzoddev.mvi.data.remote.dto.User

data class MviState(
    val users: List<User> = emptyList(),
    val actions: MviAction = MviAction.Idle,
    val message: String = ""
): State
