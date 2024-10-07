package pdm.demos.jokeofday

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.domain.FakeJokesService


/**
 * Represents the state of the joke screen.
 */
sealed interface JokeScreenState {
    data object Idle : JokeScreenState
    data object Loading : JokeScreenState
    data class Success(val joke: Joke) : JokeScreenState
    data class Error(val exception: Throwable) : JokeScreenState
}

class JokeScreenViewModel : ViewModel() {

    var state: JokeScreenState by mutableStateOf<JokeScreenState>(JokeScreenState.Idle)
    private val service = FakeJokesService()

    fun fetchJoke() {
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