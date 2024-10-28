package pdm.demos.guessadoodle.lobbies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pdm.demos.guessadoodle.TAG

class LobbiesListViewModel(private val db: FirebaseFirestore) : ViewModel() {

    fun fetchExistingLobbies() {
        viewModelScope.launch {
            Log.i(TAG, "Fetching lobbies ...")
            db.collection("lobbies").get().await().forEach {
                Log.i(TAG, "Lobby: ${it.id}")
            }
        }
    }

    fun listenForLobbies() {
        viewModelScope.launch {
            db.collection("lobbies").snapshots().collect {
                Log.i(TAG, "Lobbies changed: ${it.size()}")
                it.forEach {
                    Log.i(TAG, "Lobby: ${it.id}")
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LobbiesListViewModelFactory(private val db: FirebaseFirestore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LobbiesListViewModel(db) as T
    }
}
