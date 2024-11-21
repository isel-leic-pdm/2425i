package pt.isel.pdm.firebaseexplorer.model.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "simpleEntity", )
data class SimplePersistentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val aString: String,
    val aLong: Long


)