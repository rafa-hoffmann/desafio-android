package com.desafio.picpay.core.data.repository

import com.desafio.picpay.core.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    suspend fun sync()
}