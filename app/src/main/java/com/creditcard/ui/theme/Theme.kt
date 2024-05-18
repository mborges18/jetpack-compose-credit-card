package com.creditcard.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = BlueGrey40,
    tertiary = Yellow40,
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    error = Color(0xFFba1b1b),
    onError = Color(0xFFFFFFFF),
    background = Color(0xFFfdfcff),
    onBackground = Color(0xFF1b1b1b),
    surface = Color(0xFFfdfcff),
    onSurface = Color(0xFF1b1b1b),
    surfaceVariant = Color(0xFFdfe2eb),
    onSurfaceVariant = Color(0xffa6a6a6),
)

val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    secondary = BlueGrey80,
    tertiary = Yellow80,
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF1b1b1b),
    error = Color(0xFFffb4a9),
    onError = Color(0xFF680003),
    background = Color(0xff343434),
    onBackground = Color(0xff888b8f),
    surface = Color(0xff333333),
    onSurface = Color(0xFFe2e2e6),
    surfaceVariant = Color(0xff3c3e42),
    onSurfaceVariant = Color(0xffa6a6a6),
)

@Composable
fun JetPackComposeCreditCardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}