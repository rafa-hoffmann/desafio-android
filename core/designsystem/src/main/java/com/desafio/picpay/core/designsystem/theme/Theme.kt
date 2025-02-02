package com.desafio.picpay.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF11C76F),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF1D1E20),
    onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFACB1BD),
    onSecondary = Color(0xFF000000),
    tertiary = Color(0x80FFFFFF),
    onTertiary = Color(0xFF000000),
    background = Color(0xFF2B2C2F),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF2B2C2F),
    onSurface = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF11C76F),
    tertiary = Color(0x80FFFFFF),
    onTertiary = Color(0xFF000000),
)

private val Typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
    )
)

@Composable
fun PicPayTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
