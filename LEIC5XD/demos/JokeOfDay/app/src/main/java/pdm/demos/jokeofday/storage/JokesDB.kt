package pdm.demos.jokeofday.storage

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "joke")
data class JokeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "joke_text", collate = ColumnInfo.UNICODE) val content: String,
    @ColumnInfo(name = "joke_source", defaultValue = "NULL") val source: String?
)

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJokes(vararg jokes: JokeEntity)

    @Query("SELECT * FROM joke WHERE id = :id")
    fun getJokeById(id: Int): Flow<JokeEntity>

    @Query("SELECT * FROM joke LIMIT :limit OFFSET :offset")
    suspend fun getJokes(limit: Int, offset: Int): List<JokeEntity>
}

@Database(entities = [JokeEntity::class], version = 1)
abstract class JokesDB : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}