package com.oswaldoguevara.mycoffeelab.ui.beans.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Lista de café en grano (beans) del inventario (HU-02).
 *
 * Próxima unidad: Observará un Flow<List<CoffeeBean>> expuesto por
 * CoffeeBeanRepository y pintará cada item con su semáforo de frescura.
 */
@Composable
fun BeansListScreen(
    onBeanClick: (Long) -> Unit,
    onAddBeanClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    @Suppress("UNUSED_EXPRESSION") onBeanClick

    PlaceholderScreen(
        title = "Inventario de beans",
        description = "Aquí se listarán los beans con su semáforo de " +
                "frescura (verde 0-14 d, amarillo 15-28 d, rojo >28 d).",
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBeanClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar bean")
            }
        }
    )
}
