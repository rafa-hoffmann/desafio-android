package com.desafio.picpay.core.database.di

import androidx.room.Room
import com.desafio.picpay.core.database.PicPayDatabase
import com.desafio.picpay.core.database.dao.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<PicPayDatabase> {
        Room.databaseBuilder(
            androidContext(),
            PicPayDatabase::class.java,
            "picpay-database",
        ).build()
    }

    single<UserDao> {
        get<PicPayDatabase>().userDao()
    }
}