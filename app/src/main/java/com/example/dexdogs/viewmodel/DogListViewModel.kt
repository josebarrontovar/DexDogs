package com.example.dexdogs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dexdogs.api.ApiResponseStatus
import com.example.dexdogs.model.Dog
import com.example.dexdogs.repository.DogRepository
import kotlinx.coroutines.launch

class DogListViewModel() : ViewModel() {

    private val _dogList = MutableLiveData<List<Dog>>()
    val dogList: LiveData<List<Dog>> get()= _dogList

    private val _apiResponseStatus = MutableLiveData<ApiResponseStatus>()
    val apiResponseStatus: LiveData<ApiResponseStatus> get()= _apiResponseStatus


    private val dogRepository = DogRepository()

    init {
        downloadDogs()
    }


    private fun downloadDogs() {
        viewModelScope.launch {
            try{
            _apiResponseStatus.value=ApiResponseStatus.LOADING
            _dogList.value=dogRepository.downloadDogs()
                _apiResponseStatus.value=ApiResponseStatus.SUCCESS
            } catch (e: Exception){
                _apiResponseStatus.value=ApiResponseStatus.ERROR
            }
        }
    }
}