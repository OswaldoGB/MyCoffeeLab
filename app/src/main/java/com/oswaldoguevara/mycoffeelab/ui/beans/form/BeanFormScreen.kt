package com.oswaldoguevara.mycoffeelab.ui.beans.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Formulario para crear o editar un bean (HU-01, HU-09).
 *
 * Si beanId es null se interpreta como creación; si trae valor,
 * se precargarán los datos del bean para edición.
 */
@Composable
fun BeanFormScreen(
    beanId: Long?,
    onBack: () -> Unit,
    onSaved: () -> Unit,
    modifier: Modifier = Modifier,
) {
    @Suppress("UNUSED_EXPRESSION") onSaved

    val title = if (beanId == null) "Nuevo bean" else "Editar bean #$beanId"
    PlaceholderScreen(
        title = title,
        description = "Formulario con campos de nombre, origen, proceso, " +
                "fecha de compra, peso, precio, notas y foto. Validaciones " +
                "según los criterios Gherkin de HU-01.",
        modifier = modifier,
        onBack = onBack
    )
}
