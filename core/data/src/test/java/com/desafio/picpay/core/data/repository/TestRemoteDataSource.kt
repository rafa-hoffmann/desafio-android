package com.desafio.picpay.core.data.repository

import com.desafio.picpay.core.database.model.UserEntity
import com.desafio.picpay.core.network.RemoteDataSource
import com.desafio.picpay.core.network.model.NetworkUser

internal class TestRemoteDataSource : RemoteDataSource {

    private fun createUser(
        id: Int,
        name: String = "User $id",
        username: String = "user$id",
        img: String = "image_url_$id"
    ) = NetworkUser(
        id = id,
        name = name,
        username = username,
        img = img
    )

    override suspend fun getUsers() = listOf(
        createUser(1),
        createUser(2)
    )
}