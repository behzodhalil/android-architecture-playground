package uz.behzoddev.mvi_orbit.data.manager

import uz.behzoddev.mvi_orbit.data.remote.dto.User

interface MviOrbitSourceManager {
    suspend fun fetchUser(): List<User>
}