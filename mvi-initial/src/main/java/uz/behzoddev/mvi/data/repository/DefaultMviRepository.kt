package uz.behzoddev.mvi.data.repository

import uz.behzoddev.mvi.data.manager.MviSourceManager
import uz.behzoddev.mvi.data.remote.dto.User
import javax.inject.Inject

class DefaultMviRepository @Inject constructor(
    private val sourceManager: MviSourceManager
): MviRepository {

    override suspend fun fetchUsers(): List<User> {
        return sourceManager.fetchUser()
    }
}