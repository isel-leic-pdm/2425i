package pt.isel.pdm.firebaseexplorer.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pt.isel.pdm.firebaseexplorer.model.database.model.OtherEntity

@Dao()
interface OtherEntityDao {
    @Query("SELECT * FROM otherEntity")
    suspend fun getAll(): List<OtherEntity>

    @Insert
    suspend fun insertAll(vararg ents: OtherEntity)

    @Delete
    suspend fun delete(ent: OtherEntity)

    @Update
    suspend fun update(vararg ents: OtherEntity)

}