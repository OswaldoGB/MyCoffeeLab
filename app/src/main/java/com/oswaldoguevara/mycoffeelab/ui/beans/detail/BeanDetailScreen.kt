package com.oswaldoguevara.mycoffeelab.ui.beans.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oswaldoguevara.mycoffeelab.ui.common.components.PlaceholderScreen

/**
 * Detalle de un bean específico (HU-03, HU-04).
 *
 * Próxima unidad: cargará el bean por id, mostrará sus datos,
 * recetas vinculadas y permitirá marcarlo como activo.
 */
@Composable
fun BeanDetailScreen(
    beanId: Long,
    onBack: () -> Unit,
    onEditClick: () -> Unit,
    onCreateRecipeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    @Suppress("UNUSED_EXPRESSION") onCreateRecipeClick

    PlaceholderScreen(
        title = "Bean #$beanId",
        description = "Detalle del bean: origen, proceso, fecha de compra, " +
                "notas de cata, recetas asociadas y botón para activarlo.",
        modifier = modifier,
        onBack = onBack,
        actions = {
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }
        }
    )
}
