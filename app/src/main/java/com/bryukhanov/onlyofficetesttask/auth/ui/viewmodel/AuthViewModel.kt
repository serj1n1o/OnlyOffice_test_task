package com.bryukhanov.onlyofficetesttask.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryukhanov.onlyofficetesttask.auth.domain.api.AuthRepository
import com.bryukhanov.onlyofficetesttask.auth.domain.api.ClearTokenUseCase
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import com.bryukhanov.onlyofficetesttask.auth.domain.model.StatusCode
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val clearTokenUseCase: ClearTokenUseCase,
) : ViewModel() {

    private val authState = MutableLiveData<AuthState>(AuthState.Default)
    fun getAuthState(): LiveData<AuthState> = authState

    private val loginData = MutableLiveData<LoginDataState>()
    fun getLoginData(): LiveData<LoginDataState> = loginData

    fun setLoginData(data: LoginDataState) {
        loginData.postValue(data)
    }

    fun authenticate() {
        val portal = loginData.value?.portalAddress
        val email = loginData.value?.email
        val password = loginData.value?.password
        viewModelScope.launch {
            if (portal != null && email != null && password != null) {
                val resultCode = authRepository.authenticate(
                    portal,
                    AuthRequest(email, password)
                )
                processResult(resultCode)
            }
        }
    }

    fun clearAuthData() {
        clearTokenUseCase.clearAuth()
    }

    private fun processResult(code: Int) {
        if (code == StatusCode.CODE_OK) {
            authState.value = AuthState.AuthSuccess
            authState.postValue(AuthState.Default)
        } else {
            authState.postValue(AuthState.ErrorAuth(code))
        }
    }
}