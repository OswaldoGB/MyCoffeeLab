package com.oswaldoguevara.mycoffeelab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Representa un grano de café del inventario (HU-01, HU-02, HU-03).
 *
 * El campo `isActive` se indexa para acelerar las consultas frecuentes del
 * Dashboard, que necesitan recuperar el bean activo en cada apertura.
 *
 * Las fechas se almacenan como Long (epoch milisegundos) para evitar
 * problemas de zonas horarias y serialización; la capa de presentación las
 * convierte a tipos legibles (LocalDate / formatters) cuando lo necesita.
 */
@Entity(
    tableName = "coffee_beans",
    indices = [Index(value = ["isActive"])]
)
data class CoffeeBeanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,
    val origin: String,

    /** Valores válidos: "WASHED", "NATURAL", "HONEY". Mapeado en domain. */
    val process: String,

    @ColumnInfo(name = "purchase_date_epoch")
    val purchaseDateEpoch: Long,

    @ColumnInfo(name = "weight_grams")
    val weightGrams: Double,

    @ColumnInfo(name = "price_paid")
    val pricePaid: Double,

    @ColumnInfo(name = "tasting_notes")
    val tastingNotes: String? = null,

    @ColumnInfo(name = "photo_uri")
    val photoUri: String? = null,

    @ColumnInfo(name = "isActive")
    val isActive: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
