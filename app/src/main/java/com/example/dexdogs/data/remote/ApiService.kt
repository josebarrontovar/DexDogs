package com.example.dexdogs.data.remote

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.dexdogs.ApplicationController
import com.example.dexdogs.utils.BASE_URL
import com.example.dexdogs.utils.GET_ALL_DOGS_URL
import com.example.dexdogs.utils.SIGN_IN_URL
import com.example.dexdogs.utils.SIGN_UP_URL
import com.example.dexdogs.data.model.LoginDTO
import com.example.dexdogs.data.model.SingUpDTO
import com.example.dexdogs.data.model.responses.DogListApiResponse
import com.example.dexdogs.data.model.responses.SignUpApiResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private val okhttpClient = OkHttpClient.Builder()
    .addInterceptor(
        ChuckerInterceptor.Builder(ApplicationController.instance) // <-- Usa el contexto de tu app
        .collector(ChuckerCollector(ApplicationController.instance))
        .maxContentLength(250_000L)
        .redactHeaders("Authorization", "Cookie") // Oculta datos sensibles
        .alwaysReadResponseBody(true)
        .build()
    )
    .addInterceptor(ApiServiceInterceptor) // Tu interceptor personalizado
    .build()



object ApiService {
    private val retrofit = Retrofit.Builder()
        .client(okhttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create()) // Convierte JSON a objetos Kotlin
        .build()

    val retrofitService: IApiServices by lazy {
        retrofit.create(IApiServices::class.java)
    }
}