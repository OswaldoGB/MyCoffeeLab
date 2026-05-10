package com.oswaldoguevara.mycoffeelab

import android.app.Application
import com.oswaldoguevara.mycoffeelab.data.local.AppDatabase

/**
 * Clase Application principal de My Coffee Lab.
 *
 * Centraliza la inicialización perezosa de la base de datos y, en futuras
 * iteraciones, también de los repositorios. Mantener esta lógica fuera de las
 * Activities permite que los ViewModels accedan a las dependencias mediante
 * una factoría sencilla sin necesidad de Hilt en esta fase del proyecto.
 */
class MyCoffeeLabApp : Application() {

    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        // Reservado para configuración global futura (timber, crash reporting, etc.)
    }
}
