package com.oswaldoguevara.mycoffeelab.data.repository

import com.oswaldoguevara.mycoffeelab.data.local.dao.CoffeeBeanDao
import com.oswaldoguevara.mycoffeelab.data.local.entity.CoffeeBeanEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio de beans.
 *
 * Hoy actúa como pasarela delgada sobre el DAO; el patrón se deja
 * preparado para que cuando llegue la sincronización con un backend
 * o caché remoto, solo cambie este archivo y no los ViewModels.
 */
class CoffeeBeanRepository(
    private val dao: CoffeeBeanDao
) {

    fun observeAllBeans(): Flow<List<CoffeeBeanEntity>> = dao.observeAll()

    fun observeActiveBean(): Flow<CoffeeBeanEntity?> = dao.observeActive()

    fun observeBean(id: Long): Flow<CoffeeBeanEntity?> = dao.observeById(id)

    /**
     * Crea o actualiza un bean. Decide por el id: 0 => nuevo, distinto => update.
     */
    suspend fun save(bean: CoffeeBeanEntity): Long {
        return if (bean.id == 0L) {
            dao.insert(bean)
        } else {
            dao.update(bean)
            bean.id
        }
    }

    suspend fun delete(bean: CoffeeBeanEntity) = dao.delete(bean)

    suspend fun setActiveBean(id: Long) = dao.setActiveBean(id)
}
