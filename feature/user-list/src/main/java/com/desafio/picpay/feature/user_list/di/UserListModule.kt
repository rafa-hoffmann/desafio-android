package com.desafio.picpay.feature.user_list.di

import com.desafio.picpay.feature.user_list.ui.viewModel.UserListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userListModule = module {
    viewModelOf(::UserListViewModel)
}