package uz.behzoddev.mvi.data.repository

import uz.behzoddev.viper_initial.data.manager.ViperSourceManager
import uz.behzoddev.mvi.data.remote.dto.User
import javax.inject.Inject

class DefaultViperRepository @Inject constructor(
    private val sourceManager: ViperSourceManager
): ViperRepository {

    override suspend fun fetchUsers(): List<User> {
        return sourceManager.fetchUser()
    }
}