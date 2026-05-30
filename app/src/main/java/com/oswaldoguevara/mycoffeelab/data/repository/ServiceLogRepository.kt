package com.oswaldoguevara.mycoffeelab.data.repository

import com.oswaldoguevara.mycoffeelab.data.local.dao.ServiceLogDao
import com.oswaldoguevara.mycoffeelab.data.local.entity.ServiceLogEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio de bitácora de servicio.
 *
 * Expone tanto el histórico completo (HU-07) como los conteos para
 * el resumen del Dashboard (HU-10).
 */
class ServiceLogRepository(
    private val dao: ServiceLogDao
) {

    fun observeAllLogs(): Flow<List<ServiceLogEntity>> = dao.observeAll()

    fun observeLogsSince(sinceEpoch: Long): Flow<List<ServiceLogEntity>> =
        dao.observeSince(sinceEpoch)

    fun observeCountSince(sinceEpoch: Long): Flow<Int> =
        dao.observeCountSince(sinceEpoch)

    fun observeCountByResult(sinceEpoch: Long, result: String): Flow<Int> =
        dao.observeCountByResult(sinceEpoch, result)

    suspend fun insert(log: ServiceLogEntity): Long = dao.insert(log)

    suspend fun delete(log: ServiceLogEntity) = dao.delete(log)
}
