package com.desafio.picpay.core.data.model

import com.desafio.picpay.core.database.model.UserEntity
import com.desafio.picpay.core.network.model.NetworkUser

data class User(
    val img: String, val name: String, val id: Int, val username: String
)

fun NetworkUser.toUser(): User = User(
    img = img, name = name, id = id, username = username
)

fun UserEntity.toUser(): User = User(
    img = img, name = name, id = id, username = username
)

fun User.toEntity(): UserEntity = UserEntity(
    img = img, name = name, id = id, username = username
)
