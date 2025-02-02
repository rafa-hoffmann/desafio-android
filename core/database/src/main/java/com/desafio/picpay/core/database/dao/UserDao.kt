package com.desafio.picpay.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.desafio.picpay.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query(value = "SELECT * FROM users")
    fun getUserEntities(): Flow<List<UserEntity>>

    @Upsert
    suspend fun upsert(vararg userEntity: UserEntity)

    @Query("DELETE FROM users WHERE id NOT IN (:ids)")
    suspend fun deleteAllNotIn(ids: List<Int>)
}