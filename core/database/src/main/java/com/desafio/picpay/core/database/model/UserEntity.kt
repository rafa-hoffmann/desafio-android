package com.desafio.picpay.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val img: String,
    val name: String,
    val username: String
)
