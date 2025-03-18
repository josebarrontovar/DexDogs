package com.example.dexdogs.repository

import com.example.dexdogs.api.DogsApi.retrofitService
import com.example.dexdogs.api.dto.DogDTOMapper
import com.example.dexdogs.model.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository() {

    suspend fun downloadDogs(): List<Dog>{
        return withContext(Dispatchers.IO){
            val dogList = retrofitService.getAllDogs()
            val dogDTOList = dogList.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }

}