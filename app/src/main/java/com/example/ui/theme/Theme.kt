package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = CyanPrimary,
    onPrimary = Color(0xFF002028),
    primaryContainer = Color(0xFF004D5F),
    onPrimaryContainer = Color(0xFFBBE9FF),
    secondary = VioletSecondary,
    onSecondary = Color(0xFF280056),
    secondaryContainer = Color(0xFF452285),
    onSecondaryContainer = Color(0xFFEADBFF),
    tertiary = EmeraldTertiary,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = TextSecondary,
    outline = SurfaceBorderDark,
    error = TerminalRed,
    onError = Color.White
)

private val LightColorScheme = DarkColorScheme // IDE defaults to dark mode for rich developer experience

@Composable
fun PolyCodeTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
