package pt.isel.pdm.firebaseexplorer.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onErrorResume
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.firebaseexplorer.data.TestService
import pt.isel.pdm.firebaseexplorer.model.SimpleModel

class DetailViewModel(
    private val service: TestService,
    modelInfo: SimpleModel
) : ViewModel() {


    var error by mutableStateOf<Exception?>(null)
    var model by mutableStateOf(modelInfo)

    init {
        model = modelInfo
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

class DetailViewModel2(
    private val service: TestService,
    modelInfo: SimpleModel
) : ViewModel() {
    var error by mutableStateOf<Exception?>(null)

}

class DetailViewModel3(
    private val service: TestService,
    modelInfo: SimpleModel
) : ViewModel() {
    var error by mutableStateOf<Exception?>(null)

}