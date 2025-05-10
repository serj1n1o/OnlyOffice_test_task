package com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryukhanov.onlyofficetesttask.auth.domain.api.AuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val userData = MutableLiveData<UserState>()
    fun getUserData(): LiveData<UserState> = userData

    fun userData() {
        viewModelScope.launch {
            val user = authRepository.getUserData()
            if (user != null) {
                userData.postValue(UserState.Content(user))
            } else {
                userData.postValue(UserState.Empty)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}