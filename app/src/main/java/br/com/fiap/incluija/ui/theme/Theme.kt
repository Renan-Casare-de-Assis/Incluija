package br.com.fiap.incluija.ui.theme

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

// ============================================================
//  IncluiJá – Theme v2
//  Gradiente principal: Ouro → Coral → Lilás
//
//  Mapeamento dos slots Material3:
//   primary   → Coral   (ação principal, botões, FAB)
//   secondary → Ouro    (destaques, badges, salários)
//   tertiary  → Lilás   (tags, chips, status aprovado)
//   background/surface → neutros do design system
// ============================================================

private val DarkColorScheme = darkColorScheme(

    // --- Primária: Coral ---
    primary             = Coral80,          // #FFADAD – botões, FABs no dark
    onPrimary           = Ink,              // #0F0F14 – texto sobre primary
    primaryContainer    = CoralDark,        // #E04848 – containers primários dark
    onPrimaryContainer  = CoralLight,       // #FFE5E5 – texto sobre primaryContainer

    // --- Secundária: Ouro ---
    secondary           = Gold80,           // #FFE599 – elementos secundários dark
    onSecondary         = Ink,              // #0F0F14 – texto sobre secondary
    secondaryContainer  = GoldDark,         // #E6B83E – containers secundários dark
    onSecondaryContainer = GoldLight,       // #FFF6DC – texto sobre secondaryContainer

    // --- Terciária: Lilás ---
    tertiary            = Violet80,         // #DFB3FF – chips, tags, status dark
    onTertiary          = Ink,              // #0F0F14 – texto sobre tertiary
    tertiaryContainer   = VioletDark,       // #A855E8 – containers terciários dark
    onTertiaryContainer = VioletLight,      // #F3E5FF – texto sobre tertiaryContainer

    // --- Backgrounds & Surfaces ---
    background          = Ink,              // #0F0F14 – fundo principal
    onBackground        = OffWhite,         // #F8F7FF – texto sobre background
    surface             = InkCard,          // #22222F – cards e superfícies
    onSurface           = OffWhite,         // #F8F7FF – texto sobre surface
    surfaceVariant      = InkMid,           // #1A1A26 – headers, drawers
    onSurfaceVariant    = GrayMid,          // #8A8A9E – textos secundários

    // --- Outline & Borders ---
    outline             = InkBorder,        // #2E2E40 – bordas, divisores
    outlineVariant      = InkBorder,        // #2E2E40 – bordas suaves

    // --- Error ---
    error               = Coral40,          // #FF6B6B – erros (coral principal)
    onError             = Ink,
    errorContainer      = CoralDark,
    onErrorContainer    = CoralLight,

    // --- Inverse ---
    inverseSurface      = OffWhite,         // #F8F7FF
    inverseOnSurface    = Ink,              // #0F0F14
    inversePrimary      = Coral40,          // #FF6B6B
)

private val LightColorScheme = lightColorScheme(

    // --- Primária: Coral ---
    primary             = Coral40,          // #FF6B6B – botões, FABs no light ★
    onPrimary           = Surface,          // #FFFFFF – texto sobre primary
    primaryContainer    = CoralLight,       // #FFE5E5 – containers primários light
    onPrimaryContainer  = CoralDark,        // #E04848 – texto sobre primaryContainer

    // --- Secundária: Ouro ---
    secondary           = Gold40,           // #FFD166 – elementos secundários light ★
    onSecondary         = Ink,              // #0F0F14 – texto sobre secondary
    secondaryContainer  = GoldLight,        // #FFF6DC – containers secundários light
    onSecondaryContainer = GoldDark,        // #E6B83E – texto sobre secondaryContainer

    // --- Terciária: Lilás ---
    tertiary            = Violet40,         // #C77DFF – chips, tags, status light ★
    onTertiary          = Surface,          // #FFFFFF – texto sobre tertiary
    tertiaryContainer   = VioletLight,      // #F3E5FF – containers terciários light
    onTertiaryContainer = VioletDark,       // #A855E8 – texto sobre tertiaryContainer

    // --- Backgrounds & Surfaces ---
    background          = OffWhite,         // #F8F7FF – fundo principal (levemente lilás)
    onBackground        = Ink,              // #0F0F14 – texto sobre background
    surface             = Surface,          // #FFFFFF – cards e superfícies
    onSurface           = Ink,              // #0F0F14 – texto sobre surface
    surfaceVariant      = GrayLight,        // #E2E1EE – inputs, headers suaves
    onSurfaceVariant    = GrayMid,          // #8A8A9E – textos secundários

    // --- Outline & Borders ---
    outline             = GrayLight,        // #E2E1EE – bordas, divisores
    outlineVariant      = GrayLight,        // #E2E1EE – bordas suaves

    // --- Error ---
    error               = CoralDark,        // #E04848 – erros
    onError             = Surface,
    errorContainer      = CoralLight,       // #FFE5E5
    onErrorContainer    = CoralDark,        // #E04848

    // --- Inverse (snackbars, tooltips) ---
    inverseSurface      = InkMid,           // #1A1A26
    inverseOnSurface    = OffWhite,         // #F8F7FF
    inversePrimary      = Coral80,          // #FFADAD
)

@Composable
fun IncluijaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color desativado para preservar a identidade visual do IncluiJá
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else      -> LightColorScheme
    }

    // Ajusta a cor da status bar para combinar com o tema
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography   = Typography,
        content      = content
    )
}