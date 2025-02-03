package com.desafio.picpay.core.domain.use_case

import com.desafio.picpay.core.data.model.User
import com.desafio.picpay.core.data.repository.UserRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestUserRepository : UserRepository {
    private val usersFlow =
        MutableSharedFlow<List<User>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getUsers() = usersFlow

    override suspend fun sync() {
        usersFlow.emit(fakeUserListSync)
    }

    fun sendUsers(users: List<User>) {
        usersFlow.tryEmit(users)
    }
}