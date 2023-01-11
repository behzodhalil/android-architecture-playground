package uz.behzoddev.mvi_orbit.ui

import uz.behzoddev.mvi_orbit.data.remote.dto.User

data class MviOrbitState(
    val users: List<User> = emptyList()
)
