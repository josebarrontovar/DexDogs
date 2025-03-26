package com.example.dexdogs.api

import com.example.dexdogs.BASE_URL
import com.example.dexdogs.GET_ALL_DOGS_URL
import com.example.dexdogs.SIGN_IN_URL
import com.example.dexdogs.SIGN_UP_URL
import com.example.dexdogs.api.dto.LoginDTO
import com.example.dexdogs.api.dto.SingUpDTO
import com.example.dexdogs.api.responses.DogListApiResponse
import com.example.dexdogs.api.responses.SignUpApiResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService {
    @GET(GET_ALL_DOGS_URL)
    suspend fun getAllDogs(): DogListApiResponse
    @POST(SIGN_UP_URL)
    suspend fun singUp(@Body singUpDTO: SingUpDTO): SignUpApiResponse
    @POST(SIGN_IN_URL)
    suspend fun singIn(@Body singUpDTO: LoginDTO): SignUpApiResponse
}


object DogsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
