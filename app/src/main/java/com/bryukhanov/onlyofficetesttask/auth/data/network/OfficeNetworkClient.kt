package com.bryukhanov.onlyofficetesttask.auth.data.network

import com.bryukhanov.onlyofficetesttask.auth.data.dto.Response
import com.bryukhanov.onlyofficetesttask.auth.data.local.TokenStorage
import com.bryukhanov.onlyofficetesttask.auth.domain.model.AuthRequest
import com.bryukhanov.onlyofficetesttask.auth.domain.model.StatusCode
import com.bryukhanov.onlyofficetesttask.util.TokenInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OfficeNetworkClient(private val tokenStorage: TokenStorage) : NetworkClient {

    override suspend fun doRequestAuth(portalAddress: String, dto: Any?): Response {

        if (dto is AuthRequest) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = createApi(portalAddress).authenticate(dto)
                    response.apply { resultCode = response.statusCode }
                } catch (e: HttpException) {
                    Response().apply { resultCode = e.code() }
                }
            }
        } else {
            return withContext(Dispatchers.IO) {
                try {
                    val response = createApi(portalAddress).logout(portalAddress)
                    response.apply { resultCode = response.statusCode }
                } catch (e: HttpException) {
                    Response().apply { resultCode = StatusCode.CODE_FAILED }
                }
            }
        }

    }

    override suspend fun doRequestUser(portalAddress: String?): Response {
        return withContext(Dispatchers.IO) {
            try {
                if (portalAddress != null) {
                    val response = createApi(portalAddress).getProfile()
                    response.apply { resultCode = response.statusCode }
                } else {
                    Response().apply { resultCode = StatusCode.CODE_FAILED }
                }
            } catch (e: HttpException) {
                Response().apply { resultCode = StatusCode.CODE_FAILED }
            }
        }

    }

    private fun createApi(portalAddress: String): AuthOfficeApi {

        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(tokenStorage))
            .build()

        return Retrofit.Builder()
            .baseUrl(portalAddress)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthOfficeApi::class.java)
    }

}