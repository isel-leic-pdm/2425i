package pdm.demos.guessadoodle.lobbies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LobbiesListRootView(vm: LobbiesListViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Button(onClick = { vm.fetchExistingLobbies() }) {
                Text("Fetch existing lobbies")
            }
            Button(onClick = { vm.listenForLobbies() }) {
                Text("Listen for existing lobbies")
            }
        }
    }
}