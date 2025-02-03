package com.picpay.desafio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.desafio.picpay.core.designsystem.theme.PicPayTheme
import com.desafio.picpay.feature.user_list.ui.view.UserListRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicPayTheme {
                UserListRoute()
            }
        }
    }
}