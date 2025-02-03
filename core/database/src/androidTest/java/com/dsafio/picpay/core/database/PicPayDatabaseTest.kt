package com.dsafio.picpay.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.desafio.picpay.core.database.PicPayDatabase
import com.desafio.picpay.core.database.dao.UserDao
import org.junit.After
import org.junit.Before

internal abstract class PicPayDatabaseTest {

    private lateinit var db: PicPayDatabase
    protected lateinit var userDao: UserDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                PicPayDatabase::class.java,
            ).build()
        }
        userDao = db.userDao()
    }

    @After
    fun teardown() = db.close()
}