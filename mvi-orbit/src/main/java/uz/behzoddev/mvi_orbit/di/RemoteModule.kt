package uz.behzoddev.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uz.behzoddev.mvi_orbit.data.manager.DefaultMviOrbitSourceManager
import uz.behzoddev.mvi_orbit.data.manager.MviOrbitSourceManager
import uz.behzoddev.mvi_orbit.data.remote.core.MviOrbitService
import uz.behzoddev.mvi_orbit.data.repository.DefaultMviOrbitRepository
import uz.behzoddev.mvi_orbit.data.repository.MviOrbitRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://5e510330f2c0d300147c034c.mockapi.io")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMviService(retrofit: Retrofit): MviOrbitService {
        return retrofit.create(MviOrbitService::class.java)
    }

    @Provides
    @Singleton
    fun providesMviDatasource(orbitService: MviOrbitService): MviOrbitSourceManager {
        return DefaultMviOrbitSourceManager(orbitService)
    }

    @Provides
    @Singleton
    fun providesMviRepository(managerOrbit: MviOrbitSourceManager): MviOrbitRepository {
        return DefaultMviOrbitRepository(managerOrbit)
    }
}