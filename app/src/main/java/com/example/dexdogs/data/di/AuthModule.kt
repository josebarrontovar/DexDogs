package com.example.dexdogs.data.di

import com.example.dexdogs.data.remote.AuthenticationInterceptor
import com.example.dexdogs.data.remote.NetworkClient
import com.example.dexdogs.domain.repository.AuthInferace
import com.example.dexdogs.domain.repository.AuthRepository
import com.example.dexdogs.domain.repository.DogRepository
import com.example.dexdogs.domain.repository.DogTask
import com.example.dexdogs.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun providerDogTask(apiService: NetworkClient): AuthInferace {
        return AuthRepository(apiService)
    }


}

