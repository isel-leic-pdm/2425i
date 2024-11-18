package pdm.demos.guessadoodle.infrastructure

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.utils.CleanDataStoreRule

@RunWith(AndroidJUnit4::class)
class NickPreferencesRepositoryTests {

    @get:Rule
    val cleanDataStoreRule = CleanDataStoreRule()

    @Test
    fun getNick_returns_null_when_no_nick_is_stored() = runTest {
        val sut = NickPreferencesRepository(cleanDataStoreRule.dataStore)
        val nick = sut.getNick()
        assert(nick == null)
    }

    @Test
    fun updateNick_stores_nick() = runTest {
        val sut = NickPreferencesRepository(cleanDataStoreRule.dataStore)
        val expectedNick = Nick("John")
        sut.updateNick(expectedNick)
        val storedNick = sut.getNick()
        assert(storedNick == expectedNick)
    }

    @Test
    fun nickFlow_emits_null_when_no_nick_is_stored() = runTest {
        val sut = NickPreferencesRepository(cleanDataStoreRule.dataStore)
        val nick = sut.nick.first()
        assert(nick == null)
    }

    @Test
    fun nickFlow_emits_nick_when_nick_is_stored() = runTest {
        val sut = NickPreferencesRepository(cleanDataStoreRule.dataStore)
        val expectedNick = Nick("John")
        sut.updateNick(expectedNick)
        val nick = sut.nick.first()
        assert(nick == expectedNick)
    }
}