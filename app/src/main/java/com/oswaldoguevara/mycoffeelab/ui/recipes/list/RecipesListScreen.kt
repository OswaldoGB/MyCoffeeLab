package com.oswaldoguevara.mycoffeelab.ui.recipes.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Listado de recetas estandarizadas (HU-04, HU-05).
 *
 * Próxima unidad: agrupará por método (Espresso / V60) y marcará
 * con badge la receta activa de cada método.
 */
@Composable
fun RecipesListScreen(
    onRecipeClick: (Long) -> Unit,
    onAddRecipeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    @Suppress("UNUSED_EXPRESSION") onRecipeClick

    PlaceholderScreen(
        title = "Recetas",
        description = "Aquí aparecerán las recetas guardadas, agrupadas " +
                "por método (Espresso, V60) y con marca de receta activa.",
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onAddRecipeClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar receta")
            }
        }
    )
}
