package uz.behzoddev.mvi.data.repository

import uz.behzoddev.mvi.data.remote.dto.User

interface MviRepository {
    suspend fun fetchUsers(): List<User>
}