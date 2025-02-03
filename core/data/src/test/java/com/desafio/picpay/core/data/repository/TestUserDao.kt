package com.desafio.picpay.core.data.repository

import com.desafio.picpay.core.database.dao.UserDao
import com.desafio.picpay.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class TestUserDao : UserDao {
    private val entitiesStateFlow = MutableStateFlow(emptyList<UserEntity>())

    override fun getUserEntities(): Flow<List<UserEntity>> = entitiesStateFlow

    override suspend fun upsert(vararg userEntity: UserEntity) {
        entitiesStateFlow.update { currentList ->
            val currentEntities = currentList.associateBy { it.id }
            val newEntities = userEntity.associateBy { it.id }
            (currentEntities + newEntities).values.toList()
        }
    }

    override suspend fun deleteAllNotIn(ids: List<Int>) {
        entitiesStateFlow.update { list ->
            list.filter { it.id in ids }
        }
    }
}