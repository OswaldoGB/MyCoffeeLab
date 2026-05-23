package com.oswaldoguevara.mycoffeelab.ui.recipes.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Formulario para crear o editar una receta (HU-04, HU-09).
 *
 * Si recipeId es null se interpreta como creación; si trae valor,
 * se precargarán los datos para edición.
 */
@Composable
fun RecipeFormScreen(
    recipeId: Long?,
    onBack: () -> Unit,
    onSaved: () -> Unit,
    modifier: Modifier = Modifier,
) {
    @Suppress("UNUSED_EXPRESSION") onSaved

    val title = if (recipeId == null) "Nueva receta" else "Editar receta #$recipeId"
    PlaceholderScreen(
        title = title,
        description = "Selector de bean, método (Espresso / V60), dosis, " +
                "yield/agua, tiempo, temperatura, molienda y notas. " +
                "Cumple criterios Gherkin de HU-04.",
        modifier = modifier,
        onBack = onBack
    )
}
