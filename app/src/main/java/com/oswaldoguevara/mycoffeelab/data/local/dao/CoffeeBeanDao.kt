package com.oswaldoguevara.mycoffeelab.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.oswaldoguevara.mycoffeelab.data.local.entity.CoffeeBeanEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO de [CoffeeBeanEntity]. Todas las consultas devuelven Flow para que la
 * capa de presentación se mantenga reactiva ante cambios sin necesidad de
 * refrescos manuales.
 *
 * El método [setActiveBean] usa una transacción para mantener el invariante
 * de la HU-03: "máximo un bean activo a la vez".
 */
@Dao
interface CoffeeBeanDao {

    @Query("SELECT * FROM coffee_beans ORDER BY isActive DESC, purchase_date_epoch DESC")
    fun observeAll(): Flow<List<CoffeeBeanEntity>>

    @Query("SELECT * FROM coffee_beans WHERE id = :id")
    fun observeById(id: Long): Flow<CoffeeBeanEntity?>

    @Query("SELECT * FROM coffee_beans WHERE isActive = 1 LIMIT 1")
    fun observeActive(): Flow<CoffeeBeanEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(bean: CoffeeBeanEntity): Long

    @Update
    suspend fun update(bean: CoffeeBeanEntity)

    @Delete
    suspend fun delete(bean: CoffeeBeanEntity)

    /**
     * Mantiene el invariante de que solo un bean puede estar activo.
     * Implementado como transacción para evitar estados intermedios visibles
     * desde el Flow.
     */
    @Transaction
    suspend fun setActiveBean(beanId: Long) {
        clearActive()
        markActive(beanId)
    }

    @Query("UPDATE coffee_beans SET isActive = 0 WHERE isActive = 1")
    suspend fun clearActive()

    @Query("UPDATE coffee_beans SET isActive = 1 WHERE id = :id")
    suspend fun markActive(id: Long)
}
