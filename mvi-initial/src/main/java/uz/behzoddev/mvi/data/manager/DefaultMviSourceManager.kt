package uz.behzoddev.mvi.data.manager

import uz.behzoddev.mvi.data.remote.core.MviService
import uz.behzoddev.mvi.data.remote.dto.User
import javax.inject.Inject

class DefaultMviSourceManager @Inject constructor(
    private val service: MviService
) : MviSourceManager {

    override suspend fun fetchUser(): List<User> {
        return service.fetchUsers()
    }
}