package com.oswaldoguevara.mycoffeelab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Representa una receta estandarizada (HU-04, HU-05).
 *
 * Se modela como tabla única con campos opcionales (`yieldOut`, `waterTotal`)
 * en lugar de heredar dos tablas, porque son solo dos métodos y los campos
 * compartidos son la mayoría. Esto simplifica las queries de Dashboard.
 *
 * La integridad referencial con CoffeeBean se asegura con onDelete=CASCADE
 * para que al eliminar un bean (HU-09) se eliminen sus recetas asociadas y
 * no quedar con registros huérfanos.
 */
@Entity(
    tableName = "recipes",
    foreignKeys = [
        ForeignKey(
            entity = CoffeeBeanEntity::class,
            parentColumns = ["id"],
            childColumns = ["bean_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["bean_id"]), Index(value = ["method"])]
)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,

    @ColumnInfo(name = "bean_id")
    val beanId: Long,

    /** Valores válidos: "ESPRESSO", "V60". */
    val method: String,

    @ColumnInfo(name = "dose_in_grams")
    val doseInGrams: Double,

    /** Solo para espresso. Null cuando method = V60. */
    @ColumnInfo(name = "yield_out_grams")
    val yieldOutGrams: Double? = null,

    /** Solo para V60. Null cuando method = ESPRESSO. */
    @ColumnInfo(name = "water_total_ml")
    val waterTotalMl: Double? = null,

    @ColumnInfo(name = "time_seconds")
    val timeSeconds: Int,

    val temperature: Double,

    @ColumnInfo(name = "grind_description")
    val grindDescription: String? = null,

    val notes: String? = null,

    @ColumnInfo(name = "isActive")
    val isActive: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
