package pt.isel.pdm.firebaseexplorer.model.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "otherEntity",
    foreignKeys = [
        ForeignKey(
            entity = SimplePersistentEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentId"],
            onDelete = ForeignKey.CASCADE // Automatically delete otherEntity when a SimplePersistentEntity is deleted
        )
    ]
)
data class OtherEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val parentId : Int = 0,
    val number : Int
)