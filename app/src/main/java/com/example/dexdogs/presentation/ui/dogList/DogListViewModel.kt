package com.example.dexdogs.presentation.ui.dogList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.domain.model.Dog
import com.example.dexdogs.domain.repository.DogTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(private val dogRepository: DogTask) : ViewModel() {

    private val _dogList = MutableLiveData<List<Dog>>()
    val dogList: LiveData<List<Dog>> get() = _dogList

    private val _apiResponseStatus = MutableLiveData<ApiResponseStatus<Any>>()
    val apiResponseStatus: LiveData<ApiResponseStatus<Any>> get() = _apiResponseStatus

    init {
        getDogsCollection()
    }

    private fun getDogsCollection() {
        viewModelScope.launch {
            _apiResponseStatus.value = ApiResponseStatus.Loading()
            handleResponseStatus(dogRepository.getDogCollection())
        }
    }

    fun addDogToUser(dogId: Long) {
        viewModelScope.launch {
            _apiResponseStatus.value = ApiResponseStatus.Loading()
            handleAddDogToUserResponseStatus(dogRepository.addDogToUser(dogId))
        }
    }

    private fun handleResponseStatus(downloadDogs: ApiResponseStatus<List<Dog>>) {
        if (downloadDogs is ApiResponseStatus.Success) {
            _dogList.value = downloadDogs.data
        }
        _apiResponseStatus.value = downloadDogs as ApiResponseStatus<Any>

    }

    private fun handleAddDogToUserResponseStatus(downloadDogs: ApiResponseStatus<Any>) {
        if (downloadDogs is ApiResponseStatus.Success) {
            getDogsCollection()
        }
        _apiResponseStatus.value = downloadDogs

    }
}