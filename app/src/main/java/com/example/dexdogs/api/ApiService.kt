package com.example.dexdogs.api

import com.example.dexdogs.BASE_URL
import com.example.dexdogs.api.responses.DogListApiResponse
import com.example.dexdogs.api.responses.DogListResponse
import com.example.dexdogs.model.Dog
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService {
    @GET("dogs")
    suspend fun getAllDogs(): DogListApiResponse
}


object DogsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
