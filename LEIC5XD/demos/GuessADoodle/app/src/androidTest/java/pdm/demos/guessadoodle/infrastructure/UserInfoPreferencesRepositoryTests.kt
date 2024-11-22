package pdm.demos.guessadoodle.infrastructure

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.utils.CleanDataStoreRule

@RunWith(AndroidJUnit4::class)
class UserInfoPreferencesRepositoryTests {

    @get:Rule
    val cleanDataStoreRule = CleanDataStoreRule()

    @Test
    fun getUserInfo_returns_null_when_no_userInfo_is_stored() = runTest {
        val sut = UserInfoPreferencesRepository(cleanDataStoreRule.dataStore)
        val userInfo = sut.getUserInfo()
        assert(userInfo == null)
    }

    @Test
    fun updateUserInfo_stores_the_info() = runTest {
        val sut = UserInfoPreferencesRepository(cleanDataStoreRule.dataStore)
        val expectedUserInfo = UserInfo(Nick("John"), "The Picasso of doodling")
        sut.updateUserInfo(expectedUserInfo)
        val storedNick = sut.getUserInfo()
        assert(storedNick == expectedUserInfo)
    }

    @Test
    fun userInfoFlow_emits_null_when_no_info_is_stored() = runTest {
        val sut = UserInfoPreferencesRepository(cleanDataStoreRule.dataStore)
        val userInfo = sut.userInfo.first()
        assert(userInfo == null)
    }

    @Test
    fun userInfoFlow_emits_info_when_it_is_stored() = runTest {
        val sut = UserInfoPreferencesRepository(cleanDataStoreRule.dataStore)
        val expectedUserInfo = UserInfo(Nick("John"))
        sut.updateUserInfo(expectedUserInfo)
        val userInfo = sut.userInfo.first()
        assert(userInfo == expectedUserInfo)
    }
}