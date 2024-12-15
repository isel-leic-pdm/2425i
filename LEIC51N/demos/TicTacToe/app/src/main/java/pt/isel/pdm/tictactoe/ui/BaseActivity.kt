package pt.isel.pdm.tictactoe.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import pt.isel.pdm.tictactoe.DependencyContainer

abstract class BaseActivity() : ComponentActivity() {

    private lateinit var backButtonCallback: OnBackPressedCallback

    protected val dependencyContainer by lazy { (application as DependencyContainer) }

    protected val activityTag: String = this.javaClass.simpleName

    protected var backAware: Boolean
        get() = backButtonCallback.isEnabled
        set(enable) {
            backButtonCallback.isEnabled = enable
        }


    public inline fun <reified T> navigate(
        noinline apply: ((Intent) -> Unit)? = null
    ) {
        val intent = Intent(this, T::class.java)

        if (apply != null)
            apply(intent)

        this.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(activityTag, "onCreate")
        super.onCreate(savedInstanceState)

        backButtonCallback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                backPressed()
            }

        }

        onBackPressedDispatcher.addCallback(this, backButtonCallback)
    }


    override fun onStart() {
        Log.d(activityTag, "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d(activityTag, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(activityTag, "onDestroy")
        super.onDestroy()
    }

    protected open fun backPressed() {}

}