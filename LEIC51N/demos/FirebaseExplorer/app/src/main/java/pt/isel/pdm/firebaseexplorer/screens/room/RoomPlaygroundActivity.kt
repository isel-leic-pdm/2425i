package pt.isel.pdm.firebaseexplorer.screens.room

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pt.isel.pdm.firebaseexplorer.DependencyContainer
import pt.isel.pdm.firebaseexplorer.FirebaseExplorerApplication
import pt.isel.pdm.firebaseexplorer.model.database.model.SimplePersistentEntity
import pt.isel.pdm.firebaseexplorer.screens.components.TextButton
import pt.isel.pdm.firebaseexplorer.screens.flow.FlowPlaygroundActivity
import pt.isel.pdm.firebaseexplorer.screens.helpers.viewModelInit
import pt.isel.pdm.firebaseexplorer.screens.theme.FirebaseExplorerTheme

class RoomPlaygroundActivity : ComponentActivity() {

    val viewModel by viewModels<RoomPlaygroundViewModel> {
        viewModelInit {
            RoomPlaygroundViewModel((application as DependencyContainer).database)
        }
    }

    companion object {
        fun navigate(ctx: Context) {
            val intent = Intent(ctx, RoomPlaygroundActivity::class.java)
            ctx.startActivity(intent)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseExplorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RoomPlaygroundScreen(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}


@Composable
private fun RoomPlaygroundScreen(
    viewModel: RoomPlaygroundViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    )
    {
        TextButton(
            str = "Refresh",
            click = viewModel::refresh
        )

        TextButton(
            str = "Add",
            click = viewModel::addNew
        )

        Column(modifier = Modifier.fillMaxSize()) {

            viewModel.tuplesFlows.collectAsStateWithLifecycle().value.forEach { t ->
                SimpleEntityView(
                    entity = t,
                    onUpdate = { viewModel.update(t) },
                    onDelete = { viewModel.delete(t) })
            }
            /*
            viewModel.tuples.forEach { t ->
                SimpleEntityView(
                    entity = t,
                    onUpdate = { viewModel.update(t) },
                    onDelete = { viewModel.delete(t) })
            }*/

        }

    }
}

@Composable
fun SimpleEntityView(
    entity: SimplePersistentEntity,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("${entity.id} | ${entity.aString} | ${entity.aLong}")
        }

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onUpdate) {
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }

    }

}
