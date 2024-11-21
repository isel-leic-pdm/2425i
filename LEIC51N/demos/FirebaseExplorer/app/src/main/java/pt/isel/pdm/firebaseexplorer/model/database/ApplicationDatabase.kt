package pt.isel.pdm.firebaseexplorer.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.isel.pdm.firebaseexplorer.model.database.dao.OtherEntityDao
import pt.isel.pdm.firebaseexplorer.model.database.dao.SimpleModelDao
import pt.isel.pdm.firebaseexplorer.model.database.dao.SimpleModelWithOtherDao
import pt.isel.pdm.firebaseexplorer.model.database.model.OtherEntity
import pt.isel.pdm.firebaseexplorer.model.database.model.SimplePersistentEntity


@Database(version = 3, entities = [SimplePersistentEntity::class, OtherEntity::class])
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun simpleModelDao(): SimpleModelDao



















    abstract fun otherEntityDao(): OtherEntityDao
    abstract fun simpleModelWithOtherDao() : SimpleModelWithOtherDao
}