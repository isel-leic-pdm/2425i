package pt.isel.pdm.firebaseexplorer.screens.room

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.firebaseexplorer.model.database.ApplicationDatabase
import pt.isel.pdm.firebaseexplorer.model.database.model.OtherEntity
import pt.isel.pdm.firebaseexplorer.model.database.model.SimplePersistentEntity
import java.util.UUID

class RoomPlaygroundViewModel(
    private val db: ApplicationDatabase
) : ViewModel() {

    val tuples = mutableStateListOf<SimplePersistentEntity>()

    val tuplesFlows = db.simpleModelDao().getAllFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun refresh() {
        viewModelScope.launch {
            val data = db.simpleModelDao().getAll()
            tuples.clear()
            tuples.addAll(data)
        }
    }

    fun addNew() {
        viewModelScope.launch {
            db.simpleModelWithOtherDao().insertUserAndOtherEntity(
                SimplePersistentEntity(
                    aString = UUID.randomUUID().toString(),
                    aLong = System.currentTimeMillis(),
                ),
                listOf(
                    OtherEntity(number = 1),
                    OtherEntity(number = 2),
                    OtherEntity(number = 3)
                )
            )

            db.simpleModelDao().insertAll(
                SimplePersistentEntity(
                    aString = UUID.randomUUID().toString(),
                    aLong = System.currentTimeMillis()
                )
            )

        }
    }

    fun update(t: SimplePersistentEntity) {
        viewModelScope.launch {
            db.simpleModelDao().update(
                t.copy(aString = "+1" + t.aString, aLong = t.aLong + 1)
            )
        }
    }

    fun delete(t: SimplePersistentEntity) {
        viewModelScope.launch {
            db.simpleModelDao().delete(t)

        }
    }
}