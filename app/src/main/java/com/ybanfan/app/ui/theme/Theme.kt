package com.ybanfan.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Paper = Color(0xFFF2EADB)
val PaperShade = Color(0xFFE6DAC3)
val Ink = Color(0xFF2A1D14)
val InkMuted = Color(0xFF6B5A4A)
val CarpetRed = Color(0xFF8E2A1E)
val Mustard = Color(0xFFC4922A)
val Moss = Color(0xFF51693A)

private val colors = lightColorScheme(
    primary = CarpetRed,
    onPrimary = Paper,
    secondary = Moss,
    onSecondary = Paper,
    background = Paper,
    onBackground = Ink,
    surface = Paper,
    onSurface = Ink,
    surfaceVariant = PaperShade,
    onSurfaceVariant = InkMuted,
    outlineVariant = Ink.copy(alpha = 0.18f),
)

private val serif = FontFamily.Serif

private val typography = Typography().copy(
    headlineLarge = TextStyle(fontFamily = serif, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 38.sp),
    headlineSmall = TextStyle(fontFamily = serif, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp),
    titleLarge = TextStyle(fontFamily = serif, fontWeight = FontWeight.Bold, fontSize = 22.sp, lineHeight = 28.sp),
    titleMedium = TextStyle(fontFamily = serif, fontWeight = FontWeight.SemiBold, fontSize = 17.sp, lineHeight = 23.sp),
    bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontSize = 15.sp, lineHeight = 22.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 13.sp, letterSpacing = 1.4.sp),
    labelMedium = TextStyle(fontSize = 13.sp, lineHeight = 18.sp),
)

@Composable
fun YbanTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colors, typography = typography, content = content)
}
