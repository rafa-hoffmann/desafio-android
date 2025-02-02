package com.desafio.picpay.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.desafio.picpay.core.database.dao.UserDao
import com.desafio.picpay.core.database.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class PicPayDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}