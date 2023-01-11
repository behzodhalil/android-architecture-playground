package uz.behzoddev.mvi_orbit.data.manager

import uz.behzoddev.mvi_orbit.data.remote.core.MviOrbitService
import uz.behzoddev.mvi_orbit.data.remote.dto.User
import javax.inject.Inject

class DefaultMviOrbitSourceManager @Inject constructor(
    private val orbitService: MviOrbitService
) : MviOrbitSourceManager {

    override suspend fun fetchUser(): List<User> {
        return orbitService.fetchUsers()
    }
}