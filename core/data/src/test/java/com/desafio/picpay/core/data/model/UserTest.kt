package com.desafio.picpay.core.data.model

import com.desafio.picpay.core.database.model.UserEntity
import com.desafio.picpay.core.network.model.NetworkUser
import org.junit.Test
import kotlin.test.assertEquals

internal class UserTest {

    @Test
    fun userToEntityModel() {
        val user = User(
            img = "https://image.url/image.jpg",
            name = "Juanita McKinney",
            id = 1,
            username = "Sammy Sheppard"
        )
        val entity = user.toEntity()

        assertEquals(1, entity.id)
        assertEquals("Juanita McKinney", entity.name)
        assertEquals("Sammy Sheppard", entity.username)
        assertEquals("Juanita McKinney", entity.name)
        assertEquals("https://image.url/image.jpg", entity.img)
    }

    @Test
    fun entityToUserModel() {
        val entity = UserEntity(
            img = "https://image.url/image.jpg",
            name = "Juanita McKinney",
            id = 1,
            username = "Sammy Sheppard"
        )

        val expected = User(
            img = "https://image.url/image.jpg",
            name = "Juanita McKinney",
            id = 1,
            username = "Sammy Sheppard"
        )

        assertEquals(expected, entity.toUser())
    }

    @Test
    fun networkToUserModel() {
        val network = NetworkUser(
            img = "https://image.url/image.jpg",
            name = "Juanita McKinney",
            id = 1,
            username = "Sammy Sheppard"
        )

        val expected = User(
            img = "https://image.url/image.jpg",
            name = "Juanita McKinney",
            id = 1,
            username = "Sammy Sheppard"
        )

        assertEquals(expected, network.toUser())
    }
}