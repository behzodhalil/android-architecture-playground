package uz.behzoddev.mvi_orbit.data.repository

import uz.behzoddev.mvi_orbit.data.remote.dto.User

interface MviOrbitRepository {
    suspend fun fetchUsers(): List<User>
}