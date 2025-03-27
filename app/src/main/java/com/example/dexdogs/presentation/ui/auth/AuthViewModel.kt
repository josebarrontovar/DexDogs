package com.example.dexdogs.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dexdogs.data.remote.ApiResponseStatus
import com.example.dexdogs.domain.model.User
import com.example.dexdogs.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _apiResponseStatus = MutableLiveData<ApiResponseStatus<User>>()
    val apiResponseStatus: LiveData<ApiResponseStatus<User>> get() = _apiResponseStatus

    fun singUp(email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            _apiResponseStatus.value = ApiResponseStatus.Loading()
            handleResponseStatus(authRepository.singUp(email,password,confirmPassword))
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch{
            _apiResponseStatus.value= ApiResponseStatus.Loading()
            handleResponseStatus(authRepository.login(email,password))
        }
    }



    private fun handleResponseStatus(user: ApiResponseStatus<User>) {
        if (user is ApiResponseStatus.Success) {
            _user.value = user.data
        }
        _apiResponseStatus.value = user

    }
}