package com.desafio.picpay.core.domain.use_case

import com.desafio.picpay.core.data.model.User

private fun createUser(
    id: Int,
    name: String = "User $id",
    username: String = "user$id",
    img: String = "image_url_$id"
) = User(
    id = id, name = name, username = username, img = img
)

internal val fakeUserList = listOf(createUser(1), createUser(2))

internal val fakeUserListSync = listOf(createUser(2), createUser(3))