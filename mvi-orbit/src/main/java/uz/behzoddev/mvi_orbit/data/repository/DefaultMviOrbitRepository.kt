package uz.behzoddev.mvi_orbit.data.repository

import uz.behzoddev.mvi_orbit.data.manager.MviOrbitSourceManager
import uz.behzoddev.mvi_orbit.data.remote.dto.User
import javax.inject.Inject

class DefaultMviOrbitRepository @Inject constructor(
    private val orbitSourceManager: MviOrbitSourceManager
): MviOrbitRepository {

    override suspend fun fetchUsers(): List<User> {
        return orbitSourceManager.fetchUser()
    }
}