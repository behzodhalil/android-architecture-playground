package uz.behzoddev.mvi_orbit.data.remote.core

import retrofit2.http.GET
import uz.behzoddev.mvi_orbit.data.remote.dto.User

interface MviOrbitService {
    @GET("users")
    suspend fun fetchUsers(): List<User>
}