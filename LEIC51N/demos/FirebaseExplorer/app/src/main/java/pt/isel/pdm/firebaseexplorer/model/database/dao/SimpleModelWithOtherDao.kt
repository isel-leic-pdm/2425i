package pt.isel.pdm.firebaseexplorer.model.database.dao

import android.app.Application
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import pt.isel.pdm.firebaseexplorer.model.database.ApplicationDatabase
import pt.isel.pdm.firebaseexplorer.model.database.model.OtherEntity
import pt.isel.pdm.firebaseexplorer.model.database.model.SimpleEntityWithOtherEntities
import pt.isel.pdm.firebaseexplorer.model.database.model.SimplePersistentEntity

@Dao
abstract class SimpleModelWithOtherDao(
    private val db: ApplicationDatabase
) {
    @Transaction
    open suspend fun insertUserAndOtherEntity(
        entity: SimplePersistentEntity,
        otherEntities: List<OtherEntity>
    ) {
        val id = db.simpleModelDao().insert(entity).toInt()

        val otherEntitiesToAdd = otherEntities.map {
            it.copy(parentId = id)
        }

        db.otherEntityDao().insertAll(*otherEntitiesToAdd.toTypedArray())

    }







    @Transaction
    @Query("SELECT * FROM simpleEntity")
    abstract suspend fun getOneToMany(): List<SimpleEntityWithOtherEntities>

    @Transaction
    @Query("SELECT * FROM simpleEntity")
    abstract suspend fun getOneToManyMap(): Map<SimplePersistentEntity, List<SimpleEntityWithOtherEntities>>



}