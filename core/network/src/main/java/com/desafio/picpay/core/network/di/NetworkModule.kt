package com.desafio.picpay.core.network.di

import com.desafio.picpay.core.network.RemoteDataSource
import com.desafio.picpay.core.network.retrofit.PicPayNetwork
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single<RemoteDataSource> {
        PicPayNetwork(get())
    }
}