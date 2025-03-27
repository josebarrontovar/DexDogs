package com.example.dexdogs.domain.repository

import com.example.dexdogs.data.mapper.DogDTOMapper
import com.example.dexdogs.data.model.AddDogToUserDTO
import com.example.dexdogs.data.remote.NetworkClient
import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.data.remote.makeNetworkCall
import com.example.dexdogs.domain.model.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject



interface DogTask {
    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>>
    suspend fun addDogToUser(dogId: Long): ApiResponseStatus<Any>
    suspend fun getUserDog(): ApiResponseStatus<List<Dog>>
    suspend fun getDogCollection(): ApiResponseStatus<List<Dog>>
}

class DogRepository @Inject constructor(private val apiService: NetworkClient): DogTask {

    override suspend fun getDogCollection(): ApiResponseStatus<List<Dog>> {
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
        if (dogUserList.isEmpty()) {
            return dogList
        }
        return dogList.map {
            if (dogUserList.contains(it)) {
                it
            } else {
                Dog(0, it.index, "", ", ", "", "", "", "", "", "", "", false)
            }
        }.sortedBy { it.index }
    }


    override suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> {
        return makeNetworkCall {
            val dogList = apiService.retrofitService.getAllDogs()
            val dogDTOList = dogList.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }

    override suspend fun addDogToUser(dogId: Long): ApiResponseStatus<Any> {
        return makeNetworkCall {
            val addDogToUserDto = AddDogToUserDTO(dogId)
            val defaultResponse = apiService.retrofitService.addDogToUser("true", addDogToUserDto)
            if (!defaultResponse.is_success) {
                throw Exception(defaultResponse.message)
            }
        }
    }

    override suspend fun getUserDog(): ApiResponseStatus<List<Dog>> {
        return makeNetworkCall {
            val dogList = apiService.retrofitService.getUserDogs()
            val dogDTOList = dogList.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }
}
