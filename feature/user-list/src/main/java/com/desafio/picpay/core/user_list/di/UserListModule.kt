package com.desafio.picpay.core.user_list.di

import com.desafio.picpay.core.user_list.ui.viewModel.UserListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userListModule = module {
    viewModelOf(::UserListViewModel)
}