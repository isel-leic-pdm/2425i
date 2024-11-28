package pdm.demos.guessadoodle.preferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pdm.demos.guessadoodle.DependenciesContainer

class PreferencesActivity : ComponentActivity() {

    private val userInfoRepository by lazy { (application as DependenciesContainer).userInfoRepository }

    private val viewModel by viewModels<PreferencesViewModel>(
        factoryProducer = { PreferencesViewModelFactory(userInfoRepository) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadData()

        setContent {
            PreferencesRootView(viewModel, onBackIntent = { finish() })
        }
    }
}