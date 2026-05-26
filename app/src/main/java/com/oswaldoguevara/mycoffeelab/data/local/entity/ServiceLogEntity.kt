package com.oswaldoguevara.mycoffeelab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Registro rápido de un shot servido (HU-06, HU-07).
 *
 * Se prioriza velocidad de inserción y agregaciones rápidas por día
 * (índice sobre `timestamp_epoch`). Los logs no se actualizan, solo se
 * insertan y consultan, lo que simplifica enormemente el DAO.
 *
 * Si en una iteración futura se permite editar un log incorrecto, esta
 * decisión deberá revisarse.
 */
@Entity(
    tableName = "service_logs",
    foreignKeys = [
        ForeignKey(
            entity = CoffeeBeanEntity::class,
            parentColumns = ["id"],
            childColumns = ["bean_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["bean_id"]),
        Index(value = ["recipe_id"]),
        Index(value = ["timestamp_epoch"])
    ]
)
data class ServiceLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "bean_id")
    val beanId: Long,

    @ColumnInfo(name = "recipe_id")
    val recipeId: Long,

    /** Valores válidos: "GOOD", "OK", "BAD". */
    val result: String,

    val notes: String? = null,

    @ColumnInfo(name = "timestamp_epoch")
    val timestampEpoch: Long = System.currentTimeMillis()
)
