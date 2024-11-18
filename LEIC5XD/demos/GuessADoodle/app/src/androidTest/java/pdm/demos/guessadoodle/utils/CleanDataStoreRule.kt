package pdm.demos.guessadoodle.utils

import androidx.datastore.preferences.core.edit
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import pdm.demos.guessadoodle.DependenciesContainer

/**
 * This class is a JUnit rule that ensures that the provided data store is cleaned up after each test.
 */
class CleanDataStoreRule : TestRule {

    val dataStore by lazy {
        val dependenciesContainer = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as DependenciesContainer
        dependenciesContainer.preferencesDataStore
    }

    override fun apply(test: Statement, description: Description) =
        object : Statement() {
            override fun evaluate() = runTest {
                try {
                    test.evaluate()
                } finally {
                    dataStore.edit {
                        it.clear()
                    }
                }
            }
        }

}