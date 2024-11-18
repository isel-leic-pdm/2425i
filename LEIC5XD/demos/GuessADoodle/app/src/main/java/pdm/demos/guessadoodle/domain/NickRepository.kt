package pdm.demos.guessadoodle.domain

import kotlinx.coroutines.flow.Flow

interface NickRepository {
    val nick: Flow<Nick?>
    suspend fun getNick(): Nick?
    suspend fun updateNick(nick: Nick)
}