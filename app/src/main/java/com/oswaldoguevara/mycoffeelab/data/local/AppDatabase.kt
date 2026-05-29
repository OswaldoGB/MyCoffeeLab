package com.oswaldoguevara.mycoffeelab.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oswaldoguevara.mycoffeelab.data.local.dao.CoffeeBeanDao
import com.oswaldoguevara.mycoffeelab.data.local.dao.RecipeDao
import com.oswaldoguevara.mycoffeelab.data.local.dao.ServiceLogDao
import com.oswaldoguevara.mycoffeelab.data.local.entity.CoffeeBeanEntity
import com.oswaldoguevara.mycoffeelab.data.local.entity.RecipeEntity
import com.oswaldoguevara.mycoffeelab.data.local.entity.ServiceLogEntity

/**
 * Base de datos Room de la aplicación.
 *
 * Versionado en 1 ya que la app aún no ha publicado nada. Para futuras
 * migraciones, agregar `migrations` al builder y subir la versión.
 *
 * `fallbackToDestructiveMigration` se deja activo durante la fase de
 * desarrollo para no obstaculizar el avance; se debe retirar antes del
 * primer release público.
 */
@Database(
    entities = [
        CoffeeBeanEntity::class,
        RecipeEntity::class,
        ServiceLogEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coffeeBeanDao(): CoffeeBeanDao
    abstract fun recipeDao(): RecipeDao
    abstract fun serviceLogDao(): ServiceLogDao

    companion object {
        private const val DB_NAME = "mycoffeelab.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()  // TODO: retirar antes de release
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
