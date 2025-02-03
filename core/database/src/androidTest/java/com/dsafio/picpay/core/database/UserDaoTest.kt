package com.dsafio.picpay.core.database

import com.desafio.picpay.core.database.model.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class UserDaoTest : PicPayDatabaseTest() {

    private fun createUser(
        id: Int,
        name: String = "User $id",
        username: String = "user$id",
        img: String = "image_url_$id"
    ) = UserEntity(
        id = id,
        name = name,
        username = username,
        img = img
    )

    @Test
    fun getUserEntities_shouldEmitAllUsers() = runTest {
        val user1 = createUser(1)
        val user2 = createUser(2)
        userDao.upsert(user1, user2)

        val dbUsers = userDao.getUserEntities().first()
        assertContentEquals(dbUsers, listOf(user1, user2))
    }

    @Test
    fun upsertUserEntity_shouldUpdateExistingUser() = runTest {
        val user = createUser(1)
        val updatedUser = user.copy(img = "new_image_url")

        userDao.upsert(user)
        userDao.upsert(updatedUser)

        val dbUser = userDao.getUserEntities().first().first()
        assertEquals(dbUser, updatedUser)
    }

    @Test
    fun deleteAllNotIn_shouldRemoveUsersNotInCollection() = runTest {
        val user1 = createUser(1)
        val user2 = createUser(2)
        userDao.upsert(user1, user2)

        userDao.deleteAllNotIn(listOf(3, 4))

        assertContentEquals(userDao.getUserEntities().first(), listOf())
    }

    @Test
    fun deleteAllNotIn_shouldKeepUsersInCollection() = runTest {
        val user1 = createUser(1)
        val user2 = createUser(2)
        userDao.upsert(user1, user2)

        userDao.deleteAllNotIn(listOf(user1.id, user2.id))

        assertContentEquals(userDao.getUserEntities().first(), listOf(user1, user2))
    }
}