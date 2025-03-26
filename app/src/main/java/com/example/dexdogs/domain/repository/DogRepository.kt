package com.example.dexdogs.domain.repository

import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.data.remote.ApiService.retrofitService
import com.example.dexdogs.data.mapper.DogDTOMapper
import com.example.dexdogs.data.model.AddDogToUserDTO
import com.example.dexdogs.data.remote.makeNetworkCall
import com.example.dexdogs.domain.model.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DogRepository {

    suspend fun getDogCollection(): ApiResponseStatus<List<Dog>> {
        return withContext(Dispatchers.IO) {
            val dogListDeffered = async{downloadDogs()}
            val dogUserListDeffered = async {getUserDog()}

            val dogList = dogListDeffered.await()
            val dogUserList = dogUserListDeffered.await()

            if (dogList is ApiResponseStatus.Error) {
                return@withContext ApiResponseStatus.Error("JEJE")
            } else if (dogUserList is ApiResponseStatus.Error) {
                return@withContext ApiResponseStatus.Error("JEJE")
            } else if (dogList is ApiResponseStatus.Success && dogUserList is ApiResponseStatus.Success) {
                return@withContext ApiResponseStatus.Success(
                    getAllCollectionList(
                        dogList.data,
                        dogUserList.data
                    )
                )
            } else {
                return@withContext ApiResponseStatus.Error("Unknown error")
            }
        }
    }

    private fun getAllCollectionList(dogList: List<Dog>, dogUserList: List<Dog>): List<Dog> {
        return dogList.map {
            if (dogUserList.contains(it)) {
                it
            } else {
                Dog(0, it.index, "", ", ", "", "", "", "", "", "", "", false)
            }
        }.sortedBy { it.index }
    }


    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> {
        return makeNetworkCall {
            val dogList = retrofitService.getAllDogs()
            val dogDTOList = dogList.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }

    suspend fun addDogToUser(dogId: Long): ApiResponseStatus<Any> {
        return makeNetworkCall {
            val addDogToUserDto = AddDogToUserDTO(dogId)
            val defaultResponse = retrofitService.addDogToUser("true", addDogToUserDto)
            if (!defaultResponse.is_success) {
                throw Exception(defaultResponse.message)
            }
        }
    }

    suspend fun getUserDog(): ApiResponseStatus<List<Dog>> {
        return makeNetworkCall {
            val dogList = retrofitService.getUserDogs()
            val dogDTOList = dogList.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }
}
