package com.alorma.cupons.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Color(0xFFFB8C00),
    primaryVariant = Color(0xfff57c00),
    secondary = Color(0xFFCDDC39)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFFB8C00),
    primaryVariant = Color(0xfff57c00),
    secondary = Color(0xFFCDDC39)
)

@Composable
fun CuponsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes
    ) {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            val colorPrimary = if (useDarkIcons) {
                MaterialTheme.colors.primaryVariant
            } else {
                MaterialTheme.colors.background
            }
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = colorPrimary,
                    darkIcons = useDarkIcons,
                )
            }
            content()
        }
    }
}