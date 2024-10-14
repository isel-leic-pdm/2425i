package pdm.demos.jokeofday.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import pdm.demos.jokeofday.JokeTestApplication

class JokeInstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, JokeTestApplication::class.java.name, context)
    }
}