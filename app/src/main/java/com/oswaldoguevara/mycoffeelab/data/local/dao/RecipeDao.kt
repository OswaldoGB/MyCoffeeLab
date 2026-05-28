package com.oswaldoguevara.mycoffeelab.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.oswaldoguevara.mycoffeelab.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO de [RecipeEntity].
 *
 * El invariante a sostener (HU-05) es: una sola receta activa por método.
 * Por eso [setActiveRecipe] solo limpia el estado activo del mismo método
 * antes de marcar la nueva, dejando intactas las recetas activas del otro
 * método.
 */
@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY isActive DESC, created_at DESC")
    fun observeAll(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun observeById(id: Long): Flow<RecipeEntity?>

    @Query("SELECT * FROM recipes WHERE bean_id = :beanId ORDER BY created_at DESC")
    fun observeByBean(beanId: Long): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE method = :method AND isActive = 1 LIMIT 1")
    fun observeActiveByMethod(method: String): Flow<RecipeEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(recipe: RecipeEntity): Long

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Transaction
    suspend fun setActiveRecipe(recipeId: Long, method: String) {
        clearActiveForMethod(method)
        markActive(recipeId)
    }

    @Query("UPDATE recipes SET isActive = 0 WHERE method = :method AND isActive = 1")
    suspend fun clearActiveForMethod(method: String)

    @Query("UPDATE recipes SET isActive = 1 WHERE id = :id")
    suspend fun markActive(id: Long)
}
