package pt.isel.pdm.firebaseexplorer.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.firebaseexplorer.model.database.model.SimpleEntityWithOtherEntities
import pt.isel.pdm.firebaseexplorer.model.database.model.SimplePersistentEntity

@Dao()
interface SimpleModelDao {
    @Query("SELECT * FROM simpleEntity")
    suspend fun getAll(): List<SimplePersistentEntity>

    @Query("SELECT * FROM simpleEntity WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<SimplePersistentEntity>

    @Query("SELECT * FROM simpleEntity WHERE aString LIKE :str LIMIT 1")
    suspend fun findByString(str: String): SimplePersistentEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg ents: SimplePersistentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ent: SimplePersistentEntity): Long

    @Delete
    suspend fun delete(ent: SimplePersistentEntity)

    @Update
    suspend fun update(vararg ents: SimplePersistentEntity)

    @Query("SELECT * FROM simpleEntity")
    fun getAllFlow(): Flow<List<SimplePersistentEntity>>
}