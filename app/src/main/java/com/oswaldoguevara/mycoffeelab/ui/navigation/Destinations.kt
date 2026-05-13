package com.oswaldoguevara.mycoffeelab.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Rutas de navegación de la aplicación.
 *
 * Centralizadas en un único archivo para evitar 'magic strings' dispersas
 * por el código. Las rutas con argumentos siguen la convención de Navigation
 * Compose: `nombre/{argumento}`.
 */
sealed class Destination(val route: String) {

    // Pestañas principales (Bottom Navigation)
    data object Dashboard : Destination("dashboard")
    data object BeansList : Destination("beans")
    data object RecipesList : Destination("recipes")
    data object Logbook : Destination("logbook")

    // Subpantallas
    data object BeanDetail : Destination("bean_detail/{beanId}") {
        fun build(beanId: Long) = "bean_detail/$beanId"
        const val ARG = "beanId"
    }

    data object BeanForm : Destination("bean_form?beanId={beanId}") {
        fun build(beanId: Long? = null) = if (beanId == null) "bean_form" else "bean_form?beanId=$beanId"
        const val ARG = "beanId"
    }

    data object RecipeDetail : Destination("recipe_detail/{recipeId}") {
        fun build(recipeId: Long) = "recipe_detail/$recipeId"
        const val ARG = "recipeId"
    }

    data object RecipeForm : Destination("recipe_form?recipeId={recipeId}") {
        fun build(recipeId: Long? = null) = if (recipeId == null) "recipe_form" else "recipe_form?recipeId=$recipeId"
        const val ARG = "recipeId"
    }
}

/**
 * Modelo de las pestañas del Bottom Navigation. Se itera sobre esta lista en
 * [MyCoffeeLabBottomBar] para evitar duplicación.
 */
data class TopLevelDestination(
    val destination: Destination,
    val labelRes: Int,
    val icon: ImageVector
)

val topLevelDestinations: List<TopLevelDestination> = listOf(
    TopLevelDestination(Destination.Dashboard,   com.oswaldoguevara.mycoffeelab.R.string.nav_dashboard, Icons.Filled.Home),
    TopLevelDestination(Destination.BeansList,   com.oswaldoguevara.mycoffeelab.R.string.nav_beans,     Icons.Filled.Coffee),
    TopLevelDestination(Destination.RecipesList, com.oswaldoguevara.mycoffeelab.R.string.nav_recipes,   Icons.Filled.MenuBook),
    TopLevelDestination(Destination.Logbook,     com.oswaldoguevara.mycoffeelab.R.string.nav_logbook,   Icons.AutoMirrored.Filled.AutoStories)
)
