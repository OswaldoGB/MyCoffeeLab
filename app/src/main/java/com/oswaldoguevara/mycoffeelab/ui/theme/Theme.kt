package com.oswaldoguevara.mycoffeelab.ui.theme

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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Tema de la app. Mantiene la identidad de marca incluso cuando el sistema
 * tiene activo Dynamic Color: para una marca cafetera específica, preferimos
 * preservar nuestros tonos personalizados a heredar los del wallpaper.
 *
 * Si en el futuro queremos respetar el Dynamic Color del usuario, basta con
 * cambiar `dynamicColor` a `true` por defecto.
 */
private val LightColors = lightColorScheme(
    primary = CoffeePrimary,
    onPrimary = CoffeeOnPrimary,
    primaryContainer = CoffeeSecondaryLight,
    onPrimaryContainer = CoffeePrimaryDark,
    secondary = CoffeeSecondary,
    onSecondary = CoffeeOnSecondary,
    secondaryContainer = CoffeeSecondaryLight,
    background = CoffeeBackground,
    onBackground = CoffeeOnBackground,
    surface = CoffeeSurface,
    onSurface = CoffeeOnSurface,
    surfaceVariant = CoffeeSurfaceVariant,
    onSurfaceVariant = CoffeeOnSurfaceVariant,
    outline = CoffeeOutline,
    error = CoffeeError,
    onError = CoffeeOnError
)

private val DarkColors = darkColorScheme(
    primary = CoffeePrimaryDarkScheme,
    onPrimary = CoffeeOnPrimaryDarkScheme,
    secondary = CoffeeSecondary,
    background = CoffeeBackgroundDark,
    onBackground = CoffeeOnPrimary,
    surface = CoffeeSurfaceDark,
    onSurface = CoffeeOnPrimary,
    error = CoffeeError
)

@Composable
fun MyCoffeeLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val ctx = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyCoffeeLabTypography,
        content = content
    )
}
