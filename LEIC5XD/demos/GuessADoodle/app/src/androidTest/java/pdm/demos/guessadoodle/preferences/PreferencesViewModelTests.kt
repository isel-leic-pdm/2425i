package pdm.demos.guessadoodle.preferences

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.domain.UserInfoRepository
import pdm.demos.guessadoodle.utils.ReplaceMainDispatcherRule

@OptIn(ExperimentalCoroutinesApi::class)
class PreferencesViewModelTests {

    @get:Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private val testUserInfo = UserInfo(Nick("John Doe"), "Tagline")
    private val fakeRepo = object : UserInfoRepository {
        override val userInfo: Flow<UserInfo?>
            get() = flow { emit(null) }
        override suspend fun getUserInfo(): UserInfo? { delay(1000); return testUserInfo }
        override suspend fun updateUserInfo(userInfo: UserInfo) { }
    }

    @Test
    fun initial_state_is_Initialized() {
        val sut = PreferencesViewModel(userInfoRepository = fakeRepo)
        val state = sut.screenState.value
        assert(state is PreferencesScreenState.Initialized)
    }

    @Test
    fun loadData_transitions_to_Loading() {
        val sut = PreferencesViewModel(userInfoRepository = fakeRepo)
        sut.loadData()
        assert(sut.screenState.value is PreferencesScreenState.Loading) {
            "Expected state to be Loading, but was ${sut.screenState.value}"
        }
    }

    @Test
    fun when_loadData_succeeds_transitions_to_Displaying() =
        runTest(replaceMainDispatcherRule.testDispatcher) {
            val sut = PreferencesViewModel(userInfoRepository = fakeRepo)

            sut.loadData()?.join()

            assert(sut.screenState.value is PreferencesScreenState.Displaying) {
                "Expected state to be Displaying, but was ${sut.screenState.value}"
            }
        }

    @Test
    fun when_displaying_setEditing_transitions_to_Editing() {
        val sut = PreferencesViewModel(
            userInfoRepository = fakeRepo,
            initialState = PreferencesScreenState.Displaying(testUserInfo)
        )
        sut.setEditing(testUserInfo)
        assert(sut.screenState.value is PreferencesScreenState.Editing) {
            "Expected state to be Editing, but was ${sut.screenState.value}"
        }
    }

    @Test
    fun setEditing_has_no_effect_when_not_in_Displaying_state() {
        val sut = PreferencesViewModel(
            userInfoRepository = fakeRepo,
            initialState = PreferencesScreenState.Initialized
        )
        sut.setEditing(testUserInfo)
        assert(sut.screenState.value is PreferencesScreenState.Initialized) {
            "Expected state to be Initialized, but was ${sut.screenState.value}"
        }
    }

    @Test
    fun loadData_has_no_effect_when_not_in_Initialized_state() =
        runTest(replaceMainDispatcherRule.testDispatcher) {
            val sut = PreferencesViewModel(
                userInfoRepository = fakeRepo,
                initialState = PreferencesScreenState.Displaying(testUserInfo)
            )
            sut.loadData()?.join()
            assert(sut.screenState.value is PreferencesScreenState.Displaying) {
                "Expected state to be Loading, but was ${sut.screenState.value}"
            }
        }
}