package pt.isel.pdm.firebaseexplorer.model.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class SimpleEntityWithOtherEntities(
    @Embedded
    val simpleEntity: SimplePersistentEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val otherEntities: List<OtherEntity>
)