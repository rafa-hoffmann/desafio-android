package com.desafio.picpay.core.network

import com.desafio.picpay.core.network.model.NetworkUser

interface RemoteDataSource {

    suspend fun getUsers(): List<NetworkUser>
}