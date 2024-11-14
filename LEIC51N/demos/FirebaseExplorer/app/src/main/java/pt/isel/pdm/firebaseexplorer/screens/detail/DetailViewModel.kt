package pt.isel.pdm.firebaseexplorer.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import pt.isel.pdm.firebaseexplorer.data.TestService
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

class DetailViewModel(
    val service: TestService
) : ViewModel() {

    var error by mutableStateOf<Exception?>(null)
    var model by mutableStateOf<SimpleModel>(SimpleModel("none", 1, listOf()))


    fun setup(value: SimpleModel) {
        model = value

        viewModelScope.launch {
            while (true) {
                try {
                    model = service.waitForChanges(model.id)
                } catch (e: Exception) {
                    error = e
                }
            }
        }

    }

    fun dismissError() {
        error = null
    }
}