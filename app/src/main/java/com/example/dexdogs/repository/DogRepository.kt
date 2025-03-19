package com.example.dexdogs.repository

import com.example.dexdogs.api.ApiResponseStatus
import com.example.dexdogs.api.DogsApi.retrofitService
import com.example.dexdogs.api.dto.DogDTOMapper
import com.example.dexdogs.api.makeNetworkCall
import com.example.dexdogs.model.Dog

class DogRepository() {

    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> {
        return makeNetworkCall {
            val dogList = retrofitService.getAllDogs()
            val dogDTOList = dogList.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }

}