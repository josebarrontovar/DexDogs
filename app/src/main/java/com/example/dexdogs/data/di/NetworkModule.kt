package com.example.dexdogs.data.di

import com.example.dexdogs.data.remote.AuthenticationInterceptor
import com.example.dexdogs.data.remote.NetworkClient
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
class NetworkModule {

    @Provides
    @Singleton
    fun providerDogTask(apiService: NetworkClient): DogTask {
        return DogRepository(apiService)
    }

    @Provides
    @Singleton
    fun providerAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(authenticationInterceptor: AuthenticationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkClient(retrofit: Retrofit): NetworkClient {
        return NetworkClient(retrofit)
    }

}