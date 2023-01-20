package uz.behzoddev.viper_initial.data.manager

import uz.behzoddev.mvi.data.remote.core.ViperService
import uz.behzoddev.mvi.data.remote.dto.User
import javax.inject.Inject

class DefaultViperSourceManager @Inject constructor(
    private val service: ViperService
) : ViperSourceManager {

    override suspend fun fetchUser(): List<User> {
        return service.fetchUsers()
    }
}