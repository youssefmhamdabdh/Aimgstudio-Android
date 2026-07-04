package com.aimgstudio.ui.theme

import android.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.toArgb

object AimgTheme {
    fun parseColor(hex: String): ComposeColor {
        return try {
            ComposeColor(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            ComposeColor(0xFF6750A4)
        }
    }

    // Simple approximation of Material You palette generation based on a seed color
    fun getDynamicColorScheme(seedHex: String, isDark: Boolean): ColorScheme {
        val seed = parseColor(seedHex)
        
        return if (isDark) {
            darkColorScheme(
                primary = seed,
                secondary = seed.copy(alpha = 0.7f),
                tertiary = seed.copy(alpha = 0.5f),
                surface = ComposeColor(0xFF1C1B1F),
                background = ComposeColor(0xFF121212)
            )
        } else {
            lightColorScheme(
                primary = seed,
                secondary = seed.copy(alpha = 0.7f),
                tertiary = seed.copy(alpha = 0.5f),
                surface = ComposeColor(0xFFFEF7FF),
                background = ComposeColor(0xFFFFFFFF)
            )
        }
    }
}
