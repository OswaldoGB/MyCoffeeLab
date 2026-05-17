package com.oswaldoguevara.mycoffeelab.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Pantalla principal del barista al abrir la app.
 *
 * En entregas posteriores mostrará:
 *  - Bean activo + días de frescura (HU-08, HU-10).
 *  - Receta activa de espresso.
 *  - Conteo de shots del día.
 *  - Botón grande para registrar shot en menos de 30 s (HU-06).
 */
@Composable
fun DashboardScreen(
    onRegisterShotClick: () -> Unit,
    onNavigateToBeans: () -> Unit,
    onNavigateToRecipes: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // onNavigateToBeans / onNavigateToRecipes se enchufarán a las tarjetas
    // de acceso rápido del dashboard en la Entrega 4.
    @Suppress("UNUSED_EXPRESSION") onNavigateToBeans
    @Suppress("UNUSED_EXPRESSION") onNavigateToRecipes

    PlaceholderScreen(
        title = "Dashboard",
        description = "Aquí verás el bean activo, su frescura, " +
                "la receta activa y un acceso de un toque para registrar el shot.",
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onRegisterShotClick,
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Registrar shot") }
            )
        }
    )
}
