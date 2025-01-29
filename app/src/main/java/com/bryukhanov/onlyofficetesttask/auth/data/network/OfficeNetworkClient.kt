package com.bryukhanov.onlyofficetesttask.auth.data.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import com.bryukhanov.onlyofficetesttask.auth.domain.model.StatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class OfficeNetworkClient(private val authOfficeApi: AuthOfficeApi) : NetworkClient {

    override suspend fun doRequest(portalAddress: String, dto: Any?): Response {
        if (dto is AuthRequest) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = authOfficeApi.authenticate(portalAddress, dto)
                    response.apply { resultCode = response.statusCode }
                } catch (e: HttpException) {
                    Response().apply { resultCode = StatusCode.CODE_FAILED }
                } catch (e: IOException) {
                    Response().apply { resultCode = StatusCode.CODE_FAILED }
                }
            }
        } else {
            return withContext(Dispatchers.IO) {
                try {
                    val response = authOfficeApi.logout(portalAddress)
                    response.apply { resultCode = response.statusCode }
                } catch (e: HttpException) {
                    Response().apply { resultCode = StatusCode.CODE_FAILED }
                } catch (e: IOException) {
                    Response().apply { resultCode = StatusCode.CODE_FAILED }
                }
            }
        }

    }

}