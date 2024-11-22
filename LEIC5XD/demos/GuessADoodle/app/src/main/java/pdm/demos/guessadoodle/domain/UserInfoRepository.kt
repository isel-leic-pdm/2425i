package pdm.demos.guessadoodle.domain

import kotlinx.coroutines.flow.Flow

/**
 * Characterizes the repository for user information, responsible for regulating access to it.
 * Actual implementations depend on the type of storage that is used.
 */
interface UserInfoRepository {
    val userInfo: Flow<UserInfo?>
    suspend fun getUserInfo(): UserInfo?
    suspend fun updateUserInfo(userInfo: UserInfo)
}