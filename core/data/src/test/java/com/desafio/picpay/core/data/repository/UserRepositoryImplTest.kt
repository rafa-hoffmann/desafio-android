package com.desafio.picpay.core.data.repository

import com.desafio.picpay.core.data.model.toUser
import com.desafio.picpay.core.database.dao.UserDao
import com.desafio.picpay.core.network.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class UserRepositoryImplTest {

    private lateinit var subject: UserRepositoryImpl

    private lateinit var userDao: UserDao
    private lateinit var remoteDataSource: RemoteDataSource

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        userDao = TestUserDao()
        remoteDataSource = TestRemoteDataSource()

        subject = UserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            userDao = userDao,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun getUsers_shouldEmitUsersFromDao() = runTest {
        subject.sync()

        assertEquals(
            userDao.getUserEntities().first().map { it.toUser() },
            subject.getUsers().first()
        )
    }
}