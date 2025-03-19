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
    val dogList: LiveData<List<Dog>> get() = _dogList

    private val _apiResponseStatus = MutableLiveData<ApiResponseStatus<List<Dog>>>()
    val apiResponseStatus: LiveData<ApiResponseStatus<List<Dog>>> get() = _apiResponseStatus


    private val dogRepository = DogRepository()

    init {
        downloadDogs()
    }


    private fun downloadDogs() {
        viewModelScope.launch {
            _apiResponseStatus.value = ApiResponseStatus.Loading()
            handleResponseStatus(dogRepository.downloadDogs())
        }
    }

    private fun handleResponseStatus(downloadDogs: ApiResponseStatus<List<Dog>>) {
        if (downloadDogs is ApiResponseStatus.Success) {
            _dogList.value = downloadDogs.data
        }
        _apiResponseStatus.value = downloadDogs

    }
}