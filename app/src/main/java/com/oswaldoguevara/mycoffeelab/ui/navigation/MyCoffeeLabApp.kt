package com.oswaldoguevara.mycoffeelab.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * Composable raíz de la aplicación.
 *
 * Mantiene el Scaffold con la barra inferior y delega el contenido al
 * NavHost definido en [MyCoffeeLabNavHost]. La barra inferior se oculta
 * automáticamente cuando el usuario está en una pantalla de detalle o
 * formulario (rutas no top-level), siguiendo la guía de Material 3.
 */
@Composable
fun MyCoffeeLabApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = topLevelDestinations.any { it.destination.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) MyCoffeeLabBottomBar(navController, currentRoute)
        }
    ) { innerPadding ->
        MyCoffeeLabNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun MyCoffeeLabBottomBar(
    navController: NavHostController,
    currentRoute: String?
) {
    NavigationBar {
        topLevelDestinations.forEach { item ->
            val selected = currentRoute == item.destination.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.destination.route) {
                            // Evitar acumular pantallas duplicadas al cambiar de pestaña.
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(stringResource(item.labelRes)) },
                colors = NavigationBarItemDefaults.colors()
            )
        }
    }
}
