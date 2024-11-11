package pt.isel.pdm.firebaseexplorer.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.isel.pdm.firebaseexplorer.data.TestService
import pt.isel.pdm.firebaseexplorer.model.SimpleModel
import java.util.UUID


class MainViewModel(
    private val service: TestService
) : ViewModel() {

    var message by mutableStateOf("")
    var loading by mutableStateOf(false)
    var error by mutableStateOf<Exception?>(null)

    var modelList = mutableStateListOf<SimpleModel>()

    fun create() = stateAwareOperation {
        val obj = SimpleModel(
            UUID.randomUUID().toString(),
            1,
            listOf(1, 2, 3)
        )
        service.create(obj)

        message = "Created " + obj.id
        modelList.add(obj)
    }

    fun update(obj: SimpleModel) = stateAwareOperation {
        val updatedValue = obj.copy(number = obj.number + 1)
        service.update(updatedValue)
        message = "updated " + obj.id
        modelList[modelList.indexOfFirst { it.id == obj.id }] = updatedValue;
    }

    fun delete(obj: SimpleModel) = stateAwareOperation {
        service.delete(obj)
        message = "delete " + obj.id
        modelList.remove(obj)
    }

    fun getAll() {
        stateAwareOperation {
            val data = service.getAll()
            modelList.clear()
            modelList.addAll(data)
            message = ""
        }
    }


    private fun stateAwareOperation(oper: suspend () -> Unit) {
        loading = true
        viewModelScope.launch {
            try {
                oper()
            } catch (e: Exception) {
                error = e
            } finally {
                loading = false

            }
        }
    }

    fun dismissError() {
        error = null
    }
}

