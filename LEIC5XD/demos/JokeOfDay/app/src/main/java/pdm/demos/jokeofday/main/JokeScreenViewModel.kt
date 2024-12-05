package pdm.demos.jokeofday.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _stateFlow: MutableStateFlow<JokeScreenState> =
        MutableStateFlow(JokeScreenState.Idle)
    val stateFlow: StateFlow<JokeScreenState> = _stateFlow.asStateFlow()

    fun listenToJokes() {
        viewModelScope.launch {
            service.joke.collect { joke ->
                joke?.let {
                    _stateFlow.value = JokeScreenState.Loading
                    delay(1000)
                    _stateFlow.value = JokeScreenState.Success(joke)
                }
            }
        }
    }

    fun fetchJoke() {
        if (_stateFlow.value != JokeScreenState.Loading) {
            _stateFlow.value = JokeScreenState.Loading
            viewModelScope.launch {
                _stateFlow.value = try {
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
