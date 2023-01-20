package uz.behzoddev.viper_initial.data.manager

import uz.behzoddev.mvi.data.remote.dto.User

interface ViperSourceManager {
    suspend fun fetchUser(): List<User>
}