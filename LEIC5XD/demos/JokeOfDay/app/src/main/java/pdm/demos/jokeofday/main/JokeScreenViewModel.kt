package pdm.demos.jokeofday.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.domain.JokesService

/**
 * Represents the state of the joke screen.
 */
sealed interface JokeScreenState {
    data object Idle : JokeScreenState
    data object Loading : JokeScreenState
    data class Success(val joke: Joke) : JokeScreenState
    data class Error(val exception: Throwable) : JokeScreenState
}

/**
 * ViewModel for the JokeScreen. In  our design, ViewModels are responsible for hosting the
 * screen's state machine.
 */
class JokeScreenViewModel(private val service: JokesService) : ViewModel() {

    var state: JokeScreenState by mutableStateOf<JokeScreenState>(JokeScreenState.Idle)
        private set

    fun fetchJoke() {
        if (state != JokeScreenState.Loading) {
            state = JokeScreenState.Loading
            viewModelScope.launch {
                state = try {
                    val joke = service.fetchJoke()
                    JokeScreenState.Success(joke)
                } catch (e: Throwable) {
                    JokeScreenState.Error(e)
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class JokeScreenViewModelFactory(private val service: JokesService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokeScreenViewModel(service) as T
    }
}
