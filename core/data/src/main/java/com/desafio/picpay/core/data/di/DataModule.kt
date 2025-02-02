package com.desafio.picpay.core.data.di

import com.desafio.picpay.core.data.repository.UserRepository
import com.desafio.picpay.core.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }
}