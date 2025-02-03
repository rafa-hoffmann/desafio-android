package com.desafio.picpay.core.network.retrofit

import com.desafio.picpay.core.network.RemoteDataSource
import com.desafio.picpay.core.network.model.NetworkUser
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

internal interface PicPayApi {
    @GET("users")
    suspend fun getUsers(): List<NetworkUser>
}

internal class PicPayNetwork(
    networkJson: Json
) : RemoteDataSource {

    private val networkApi = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build().create(PicPayApi::class.java)

    companion object {
        private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }

    override suspend fun getUsers() = networkApi.getUsers()
}
