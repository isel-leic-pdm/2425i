package pdm.demos.guessadoodle.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import pdm.demos.guessadoodle.GuessADoodleTestApplication

@Suppress("unused")
class GuessADoodleInstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, GuessADoodleTestApplication::class.java.name, context)
    }
}