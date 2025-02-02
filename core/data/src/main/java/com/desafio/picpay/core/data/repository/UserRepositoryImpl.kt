package com.desafio.picpay.core.data.repository

import com.desafio.picpay.core.data.model.User
import com.desafio.picpay.core.data.model.toEntity
import com.desafio.picpay.core.data.model.toUser
import com.desafio.picpay.core.database.dao.UserDao
import com.desafio.picpay.core.database.model.UserEntity
import com.desafio.picpay.core.network.RemoteDataSource
import com.desafio.picpay.core.network.model.NetworkUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override fun getUsers(): Flow<List<User>> = userDao.getUserEntities().map {
        it.map(UserEntity::toUser)
    }

    override suspend fun sync() = withContext(dispatcher) {
        val networkUsers = remoteDataSource.getUsers().map(NetworkUser::toUser)
        val entityUsers = networkUsers.map(User::toEntity)

        userDao.upsert(*entityUsers.toTypedArray())
        userDao.deleteAllNotIn(entityUsers.map { it.id })
    }
}