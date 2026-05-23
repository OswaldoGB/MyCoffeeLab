package com.oswaldoguevara.mycoffeelab.ui.logbook

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Bitácora de servicio: histórico de shots/bebidas servidas (HU-07).
 *
 * Próxima unidad: línea de tiempo agrupada por día, con resumen al
 * inicio (total / buenos / malos) y filtro por bean o método.
 */
@Composable
fun LogbookScreen(
    onRegisterShotClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PlaceholderScreen(
        title = "Bitácora",
        description = "Aquí se mostrará la línea de tiempo de shots " +
                "registrados, con resumen del día (buenos/regulares/malos).",
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
