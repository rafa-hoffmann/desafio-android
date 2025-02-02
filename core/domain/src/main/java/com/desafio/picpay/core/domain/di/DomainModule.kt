package com.desafio.picpay.core.domain.di

import com.desafio.picpay.core.domain.use_case.GetUserListUseCase
import com.desafio.picpay.core.domain.use_case.SyncUserListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetUserListUseCase)
    factoryOf(::SyncUserListUseCase)
}