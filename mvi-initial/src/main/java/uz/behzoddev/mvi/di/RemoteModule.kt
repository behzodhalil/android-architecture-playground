package uz.behzoddev.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uz.behzoddev.mvi.data.manager.DefaultMviSourceManager
import uz.behzoddev.mvi.data.manager.MviSourceManager
import uz.behzoddev.mvi.data.remote.core.MviService
import uz.behzoddev.mvi.data.repository.DefaultMviRepository
import uz.behzoddev.mvi.data.repository.MviRepository
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
    fun provideMviService(retrofit: Retrofit): MviService {
        return retrofit.create(MviService::class.java)
    }

    @Provides
    @Singleton
    fun providesMviDatasource(service: MviService): MviSourceManager {
        return DefaultMviSourceManager(service)
    }

    @Provides
    @Singleton
    fun providesMviRepository(manager: MviSourceManager): MviRepository {
        return DefaultMviRepository(manager)
    }
}