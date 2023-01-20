package uz.behzoddev.mvi.data.repository

import uz.behzoddev.mvi.data.remote.dto.User

interface ViperRepository {
    suspend fun fetchUsers(): List<User>
}