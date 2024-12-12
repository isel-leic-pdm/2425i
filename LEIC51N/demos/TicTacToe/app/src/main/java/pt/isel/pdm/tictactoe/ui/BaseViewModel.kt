package pt.isel.pdm.tictactoe.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


sealed interface ViewModelOperationState {
    data object Idle : ViewModelOperationState
    data class Loading(val job: Job) : ViewModelOperationState
    data class Error(val exception: Throwable, val canRetry: Boolean = false) :
        ViewModelOperationState
}

open class BaseViewModel : ViewModel() {

    private var retryFunction: (suspend CoroutineScope.() -> Unit)? = null

    var viewOperationState by mutableStateOf<ViewModelOperationState>(ViewModelOperationState.Idle)

    protected val viewModelTag: String = this.javaClass.simpleName


    protected fun viewModelAction(
        canRetry: Boolean = false,
        function: suspend CoroutineScope.() -> Unit,
    ) {
        viewModelScope.launch {
            viewOperationState = ViewModelOperationState.Loading(coroutineContext.job)
            safeCallInternal(this, canRetry, function)
            if (viewOperationState !is ViewModelOperationState.Error)
                viewOperationState = ViewModelOperationState.Idle
        }
    }

    protected fun viewModelActionWithRetry(function: suspend CoroutineScope.() -> Unit) {
        viewModelAction(true, function)
    }


    private suspend fun safeCallInternal(
        cs: CoroutineScope,
        canRetry: Boolean = false,
        function: suspend CoroutineScope.() -> Unit,
    ) {
        try {
            function(cs)
        } catch (e: Exception) {

            if (canRetry)
                retryFunction = function

            viewOperationState = ViewModelOperationState.Error(e, canRetry)
            Log.e(viewModelTag, e.toString(), e)
        }
    }


    fun errorDismiss() {
        viewOperationState = ViewModelOperationState.Idle
    }

    fun errorRetry() {
        val retry = retryFunction;

        retryFunction = null;
        viewOperationState = ViewModelOperationState.Idle

        if (retry != null)
            viewModelActionWithRetry(function = retry)
    }

}