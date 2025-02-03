package com.picpay.desafio.android

import android.app.Application
import com.desafio.picpay.core.data.di.dataModule
import com.desafio.picpay.core.database.di.databaseModule
import com.desafio.picpay.core.domain.di.domainModule
import com.desafio.picpay.core.network.di.networkModule
import com.desafio.picpay.feature.user_list.di.userListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(
                dataModule,
                databaseModule,
                domainModule,
                networkModule,
                userListModule
            )
        }
    }
}