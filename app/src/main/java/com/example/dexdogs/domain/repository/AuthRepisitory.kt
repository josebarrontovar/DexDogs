package com.example.dexdogs.domain.repository

import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.data.model.LoginDTO
import com.example.dexdogs.data.model.SingUpDTO
import com.example.dexdogs.data.mapper.UserDTOMapper
import com.example.dexdogs.data.remote.NetworkClient
import com.example.dexdogs.data.remote.makeNetworkCall
import com.example.dexdogs.domain.model.User
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: NetworkClient) {

    suspend fun singUp(email: String, password: String, confirmPassword: String)
    : ApiResponseStatus<User> {
        return makeNetworkCall {
            val signupDTO= SingUpDTO(email,password,confirmPassword)
            val singUpResponse = apiService.retrofitService.singUp(signupDTO)
            if(!singUpResponse.is_success){
                throw Exception(singUpResponse.message)
            }
            val userDTO = singUpResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }

    suspend fun login(email: String, password: String)
            : ApiResponseStatus<User> {
        return makeNetworkCall {
            val loginDTO= LoginDTO(email,password)
            val loginResponse = apiService.retrofitService.singIn(loginDTO)
            if(!loginResponse.is_success){
                throw Exception(loginResponse.message)
            }
            val userDTO = loginResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }
}