package uz.behzoddev.viper_initial.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uz.behzoddev.viper_initial.data.manager.DefaultViperSourceManager
import uz.behzoddev.viper_initial.data.manager.ViperSourceManager
import uz.behzoddev.mvi.data.remote.core.ViperService
import uz.behzoddev.mvi.data.repository.DefaultViperRepository
import uz.behzoddev.mvi.data.repository.ViperRepository
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
    fun provideMviService(retrofit: Retrofit): ViperService {
        return retrofit.create(ViperService::class.java)
    }

    @Provides
    @Singleton
    fun providesMviDatasource(service: ViperService): ViperSourceManager {
        return DefaultViperSourceManager(service)
    }

    @Provides
    @Singleton
    fun providesMviRepository(manager: ViperSourceManager): ViperRepository {
        return DefaultViperRepository(manager)
    }
}