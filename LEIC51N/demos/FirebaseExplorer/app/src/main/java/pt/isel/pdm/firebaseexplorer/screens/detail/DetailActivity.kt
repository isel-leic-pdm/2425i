package pt.isel.pdm.firebaseexplorer.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import pt.isel.pdm.firebaseexplorer.DependencyContainer
import pt.isel.pdm.firebaseexplorer.model.SimpleModel
import pt.isel.pdm.firebaseexplorer.screens.helpers.viewModelInit
import pt.isel.pdm.firebaseexplorer.screens.main.ErrorView
import pt.isel.pdm.firebaseexplorer.screens.main.MainViewModel
import pt.isel.pdm.firebaseexplorer.screens.main.SimpleModelView
import pt.isel.pdm.firebaseexplorer.screens.theme.FirebaseExplorerTheme

class DetailActivity : ComponentActivity() {


    companion object {
        const val EXTRA_ID = "__ID"
        fun navigate(ctx: Context, model: SimpleModel) {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, model)
            ctx.startActivity(intent)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!intent.hasExtra(EXTRA_ID)) {
            finish()
            return
        }


        val model = intent.getParcelableExtra(EXTRA_ID, SimpleModel::class.java)
        val viewModel by viewModels<DetailViewModel3> {
            viewModelInit {
                DetailViewModel3(
                    (application as DependencyContainer).repository,
                    model!!
                )
            }
        }



        enableEdgeToEdge()
        setContent {
            FirebaseExplorerTheme {
                DetailScreen(
                    viewModel = viewModel,
                    onBackPressed = { finish() })
            }
        }
    }


}


var test: MutableStateFlow<Int> = MutableStateFlow(1)

@Composable
private fun DetailScreen(
    viewModel: DetailViewModel3,
    onBackPressed: () -> Unit
) {
    val model = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            SimpleModelView(model, null, null, null)
            Button(onClick = onBackPressed) {
                Text("Back")
            }
        }
    }

    if (viewModel.error != null) {
        ErrorView(
            viewModel.error!!,
            onDismiss = { viewModel.dismissError() })
    }
}