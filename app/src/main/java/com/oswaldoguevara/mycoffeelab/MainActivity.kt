package com.oswaldoguevara.mycoffeelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.oswaldoguevara.mycoffeelab.ui.navigation.MyCoffeeLabApp
import com.oswaldoguevara.mycoffeelab.ui.theme.MyCoffeeLabTheme

/**
 * Punto de entrada único de la aplicación.
 *
 * Toda la UI se construye con Jetpack Compose y la navegación se delega al
 * Composable [MyCoffeeLabApp]. Esta Activity no tiene lógica de negocio: solo
 * monta el árbol Compose y aplica el tema.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCoffeeLabTheme {
                MyCoffeeLabApp()
            }
        }
    }
}
