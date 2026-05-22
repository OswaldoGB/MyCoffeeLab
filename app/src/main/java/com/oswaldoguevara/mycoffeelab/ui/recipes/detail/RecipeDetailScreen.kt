package com.oswaldoguevara.mycoffeelab.ui.recipes.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Detalle de una receta (HU-05).
 *
 * Próxima unidad: cargará la receta, mostrará todos sus parámetros
 * (dosis, yield, tiempo, temperatura, molienda) y permitirá activarla.
 */
@Composable
fun RecipeDetailScreen(
    recipeId: Long,
    onBack: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PlaceholderScreen(
        title = "Receta #$recipeId",
        description = "Aquí se mostrarán los parámetros de la receta y " +
                "la opción de marcarla como activa para su método.",
        modifier = modifier,
        onBack = onBack,
        actions = {
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }
        }
    )
}
