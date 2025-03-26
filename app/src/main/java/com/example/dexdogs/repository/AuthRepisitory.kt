package com.example.dexdogs.repository

import com.example.dexdogs.api.ApiResponseStatus
import com.example.dexdogs.api.DogsApi.retrofitService
import com.example.dexdogs.api.dto.LoginDTO
import com.example.dexdogs.api.dto.SingUpDTO
import com.example.dexdogs.api.dto.UserDTOMapper
import com.example.dexdogs.api.makeNetworkCall
import com.example.dexdogs.model.User

class AuthRepository {

    suspend fun singUp(email: String, password: String, confirmPassword: String)
    : ApiResponseStatus<User> {
        return makeNetworkCall {
            val signupDTO= SingUpDTO(email,password,confirmPassword)
            val singUpResponse = retrofitService.singUp(signupDTO)
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
            val loginResponse = retrofitService.singIn(loginDTO)
            if(!loginResponse.is_success){
                throw Exception(loginResponse.message)
            }
            val userDTO = loginResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }
}