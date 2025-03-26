package com.example.dexdogs.data.remote

import android.provider.SyncStateContract.Constants
import com.example.dexdogs.data.model.AddDogToUserDTO
import com.example.dexdogs.data.model.LoginDTO
import com.example.dexdogs.data.model.SingUpDTO
import com.example.dexdogs.data.model.responses.DefaultResponse
import com.example.dexdogs.data.model.responses.DogListApiResponse
import com.example.dexdogs.data.model.responses.SignUpApiResponse
import com.example.dexdogs.utils.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IApiServices {

    @GET(GET_ALL_DOGS_URL)
    suspend fun getAllDogs(): DogListApiResponse
    @POST(SIGN_UP_URL)
    suspend fun singUp(@Body singUpDTO: SingUpDTO): SignUpApiResponse
    @POST(SIGN_IN_URL)
    suspend fun singIn(@Body singUpDTO: LoginDTO): SignUpApiResponse
    @POST(ADD_DOG_TO_USER)
    suspend fun addDogToUser(
        @Header("needs_authentication") needsAuthentication: String = "true",  // Encabezado
        @Body addDogToUserDTO: AddDogToUserDTO  // Cuerpo
    ): DefaultResponse

    @GET(GET_USER_DOG)
    suspend fun getUserDogs(@Header("needs_authentication") needsAuthentication: String = "true"): DogListApiResponse


}