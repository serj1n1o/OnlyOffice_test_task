package com.bryukhanov.onlyofficetesttask.auth.domain.model

object StatusCode {
    const val CODE_OK = 200
    const val CODE_NO_CONNECT = -1
    const val CODE_EMPTY = 400
    const val CODE_FAILED = 401
    const val CODE_USER_NOT_FOUND = 404
    const val CODE_MANY_LOGIN_ATTEMPTS = 429
}