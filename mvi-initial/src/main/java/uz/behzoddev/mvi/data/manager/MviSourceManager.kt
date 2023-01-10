package uz.behzoddev.mvi.data.manager

import uz.behzoddev.mvi.data.remote.dto.User

interface MviSourceManager {
    suspend fun fetchUser(): List<User>
}