package com.oswaldoguevara.mycoffeelab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.oswaldoguevara.mycoffeelab.ui.beans.detail.BeanDetailScreen
import com.oswaldoguevara.mycoffeelab.ui.beans.form.BeanFormScreen
import com.oswaldoguevara.mycoffeelab.ui.beans.list.BeansListScreen
import com.oswaldoguevara.mycoffeelab.ui.dashboard.DashboardScreen
import com.oswaldoguevara.mycoffeelab.ui.logbook.LogbookScreen
import com.oswaldoguevara.mycoffeelab.ui.recipes.detail.RecipeDetailScreen
import com.oswaldoguevara.mycoffeelab.ui.recipes.form.RecipeFormScreen
import com.oswaldoguevara.mycoffeelab.ui.recipes.list.RecipesListScreen

/**
 * Grafo de navegación principal.
 *
 * Cada destino registra una sola entrada y delega a su Composable de
 * pantalla, manteniendo este archivo como índice general fácil de auditar.
 */
@Composable
fun MyCoffeeLabNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Dashboard.route,
        modifier = modifier
    ) {
        // --- Top-level destinations -----------------------------------
        composable(Destination.Dashboard.route) {
            DashboardScreen(
                onRegisterShotClick = { /* TODO: abrir bottom sheet de registro rápido */ },
                onNavigateToBeans = { navController.navigate(Destination.BeansList.route) },
                onNavigateToRecipes = { navController.navigate(Destination.RecipesList.route) }
            )
        }

        composable(Destination.BeansList.route) {
            BeansListScreen(
                onBeanClick = { beanId -> navController.navigate(Destination.BeanDetail.build(beanId)) },
                onAddBeanClick = { navController.navigate(Destination.BeanForm.build()) }
            )
        }

        composable(Destination.RecipesList.route) {
            RecipesListScreen(
                onRecipeClick = { recipeId -> navController.navigate(Destination.RecipeDetail.build(recipeId)) },
                onAddRecipeClick = { navController.navigate(Destination.RecipeForm.build()) }
            )
        }

        composable(Destination.Logbook.route) {
            LogbookScreen(
                onRegisterShotClick = { /* TODO: abrir bottom sheet */ }
            )
        }

        // --- Sub-screens ----------------------------------------------
        composable(
            route = Destination.BeanDetail.route,
            arguments = listOf(navArgument(Destination.BeanDetail.ARG) { type = NavType.LongType })
        ) { entry ->
            val beanId = entry.arguments?.getLong(Destination.BeanDetail.ARG) ?: 0L
            BeanDetailScreen(
                beanId = beanId,
                onBack = { navController.popBackStack() },
                onEditClick = { navController.navigate(Destination.BeanForm.build(beanId)) },
                onCreateRecipeClick = { navController.navigate(Destination.RecipeForm.build()) }
            )
        }

        composable(
            route = Destination.BeanForm.route,
            arguments = listOf(
                navArgument(Destination.BeanForm.ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { entry ->
            val beanId = entry.arguments?.getLong(Destination.BeanForm.ARG)?.takeIf { it > 0 }
            BeanFormScreen(
                beanId = beanId,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = Destination.RecipeDetail.route,
            arguments = listOf(navArgument(Destination.RecipeDetail.ARG) { type = NavType.LongType })
        ) { entry ->
            val recipeId = entry.arguments?.getLong(Destination.RecipeDetail.ARG) ?: 0L
            RecipeDetailScreen(
                recipeId = recipeId,
                onBack = { navController.popBackStack() },
                onEditClick = { navController.navigate(Destination.RecipeForm.build(recipeId)) }
            )
        }

        composable(
            route = Destination.RecipeForm.route,
            arguments = listOf(
                navArgument(Destination.RecipeForm.ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { entry ->
            val recipeId = entry.arguments?.getLong(Destination.RecipeForm.ARG)?.takeIf { it > 0 }
            RecipeFormScreen(
                recipeId = recipeId,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }
    }
}
