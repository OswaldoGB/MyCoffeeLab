package com.oswaldoguevara.mycoffeelab.data.repository

import com.oswaldoguevara.mycoffeelab.data.local.dao.RecipeDao
import com.oswaldoguevara.mycoffeelab.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio de recetas.
 *
 * Encapsula la activación de receta por método (HU-05) para que los
 * ViewModels no toquen directamente el invariante transaccional.
 */
class RecipeRepository(
    private val dao: RecipeDao
) {

    fun observeAllRecipes(): Flow<List<RecipeEntity>> = dao.observeAll()

    fun observeRecipe(id: Long): Flow<RecipeEntity?> = dao.observeById(id)

    fun observeRecipesByBean(beanId: Long): Flow<List<RecipeEntity>> =
        dao.observeByBean(beanId)

    fun observeActiveByMethod(method: String): Flow<RecipeEntity?> =
        dao.observeActiveByMethod(method)

    suspend fun save(recipe: RecipeEntity): Long {
        return if (recipe.id == 0L) {
            dao.insert(recipe)
        } else {
            dao.update(recipe)
            recipe.id
        }
    }

    suspend fun delete(recipe: RecipeEntity) = dao.delete(recipe)

    suspend fun setActiveRecipe(id: Long, method: String) =
        dao.setActiveRecipe(id, method)
}
