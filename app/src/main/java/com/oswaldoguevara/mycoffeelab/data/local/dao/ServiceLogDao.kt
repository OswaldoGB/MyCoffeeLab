package com.oswaldoguevara.mycoffeelab.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oswaldoguevara.mycoffeelab.data.local.entity.ServiceLogEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO de [ServiceLogEntity]. Su carga principal son inserciones rápidas
 * desde el modal de registro (HU-06) y agregaciones por rango para el
 * Dashboard y la Bitácora (HU-07, HU-10).
 */
@Dao
interface ServiceLogDao {

    @Insert
    suspend fun insert(log: ServiceLogEntity): Long

    @Delete
    suspend fun delete(log: ServiceLogEntity)

    @Query("SELECT * FROM service_logs ORDER BY timestamp_epoch DESC")
    fun observeAll(): Flow<List<ServiceLogEntity>>

    @Query("SELECT * FROM service_logs WHERE timestamp_epoch >= :sinceEpoch ORDER BY timestamp_epoch DESC")
    fun observeSince(sinceEpoch: Long): Flow<List<ServiceLogEntity>>

    @Query("SELECT COUNT(*) FROM service_logs WHERE timestamp_epoch >= :sinceEpoch")
    fun observeCountSince(sinceEpoch: Long): Flow<Int>

    @Query("""
        SELECT COUNT(*) FROM service_logs
        WHERE timestamp_epoch >= :sinceEpoch AND result = :result
    """)
    fun observeCountByResult(sinceEpoch: Long, result: String): Flow<Int>
}
