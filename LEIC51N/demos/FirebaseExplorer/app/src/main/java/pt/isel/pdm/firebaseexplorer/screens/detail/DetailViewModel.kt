package pt.isel.pdm.firebaseexplorer.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.firebaseexplorer.services.TestService
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

    private val mutableFlow = MutableStateFlow(modelInfo)

    val state: StateFlow<SimpleModel>
        get() = mutableFlow

    init {
        viewModelScope.launch {
            service.getByIdFlow(modelInfo.id).collect{
                mutableFlow.emit(it)
            }
        }
    }
    fun dismissError() {
        error = null
    }

}

class DetailViewModel3(
    private val service: TestService,
    modelInfo: SimpleModel
) : ViewModel() {
    var error by mutableStateOf<Exception?>(null)
    fun dismissError() {
        error = null
    }

    val state = service
        .getByIdFlow(modelInfo.id)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                modelInfo)
}