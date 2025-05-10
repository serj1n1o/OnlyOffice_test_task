package com.bryukhanov.onlyofficetesttask.util

sealed class RequestResult<T>(val data: T? = null) {

    class Success<T>(data: T) : RequestResult<T>(data)
    class Error<T>() : RequestResult<T>()
}