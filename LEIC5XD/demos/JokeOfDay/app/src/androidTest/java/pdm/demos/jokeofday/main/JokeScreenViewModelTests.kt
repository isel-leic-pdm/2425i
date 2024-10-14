package pdm.demos.jokeofday.main

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

import pdm.demos.jokeofday.domain.FakeJokesService
import pdm.demos.jokeofday.domain.FetchJokeException
import pdm.demos.jokeofday.domain.JokesService
import pdm.demos.jokeofday.utils.ReplaceMainDispatcherRule

@OptIn(ExperimentalCoroutinesApi::class)
class JokeScreenViewModelTests {

    @get:Rule
    val dispatcherRule = ReplaceMainDispatcherRule()

    @Test
    fun fetchJoke_transitions_to_Loading() = runTest(dispatcherRule.testDispatcher) {
        // Arrange
        val sut = JokeScreenViewModel(FakeJokesService())

        // Act
        sut.fetchJoke()

        // Assert
        assert(
            sut.state is JokeScreenState.Loading,
            { "Expected state to be Loading, but was ${sut.state}" }
        )
    }

    @Test
    fun fetchJoke_upon_successful_completion_transitions_to_Success() =
        runTest(dispatcherRule.testDispatcher) {
            // Arrange
            val sut = JokeScreenViewModel(FakeJokesService())

            // Act
            sut.fetchJoke()
            advanceUntilIdle()

            // Assert
            assert(
                sut.state is JokeScreenState.Success,
                { "Expected state to be Success, but was ${sut.state}" }
            )
        }

    @Test
    fun fetchJoke_upon_failure_transitions_to_Error() = runTest(dispatcherRule.testDispatcher) {
        // Arrange
        val mockService = JokesService { delay(1000); throw FetchJokeException("Failed to fetch joke") }
        val viewModel = JokeScreenViewModel(mockService)

        // Act
        viewModel.fetchJoke()
        advanceUntilIdle()

        // Assert
        assert(
            viewModel.state is JokeScreenState.Error,
            { "Expected state to be Error, but was ${viewModel.state}" }
        )
    }
}