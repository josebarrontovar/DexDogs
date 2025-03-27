package com.example.dexdogs.data.remote

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkClient @Inject constructor(
    private val retrofit: Retrofit
) {

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
