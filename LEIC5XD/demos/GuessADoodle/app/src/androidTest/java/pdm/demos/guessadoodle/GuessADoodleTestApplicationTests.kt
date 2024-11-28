package pdm.demos.guessadoodle

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GuessADoodleTestApplicationTests {

    @Test
    fun instrumented_tests_use_application_test_context() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("pdm.demos.guessadoodle", context.packageName)
        assert(context.applicationContext is GuessADoodleTestApplication) {
            "Make sure the tests runner is correctly configured in build.gradle\n" +
                    "defaultConfig { testInstrumentationRunner <test runner class name> }"
        }
    }

    @Test
    fun application_context_contains_dependencies() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assert(context.applicationContext is DependenciesContainer) {
            "Make sure GuessADoodleTestApplication implements DependenciesContainer"
        }
    }
}