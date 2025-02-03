package com.desafio.picpay.core.domain.use_case

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


internal class GetUserListUseCaseTest {
    private lateinit var subject: GetUserListUseCase

    private lateinit var repository: TestUserRepository

    @Before
    fun setup() {
        repository = TestUserRepository()

        subject = GetUserListUseCase(userRepository = repository)
    }

    @Test
    fun getUserListUseCase_shouldReturnAllUsersFromRepository() = runTest {
        repository.sendUsers(fakeUserList)

        assertEquals(subject().first(), repository.getUsers().first())
    }
}