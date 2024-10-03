package com.example.diceroll.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

abstract class BaseAppActivity : ComponentActivity() {

    private val activityName by lazy { this.javaClass.simpleName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "$activityName: onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "$activityName: onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "$activityName: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "$activityName: onDestroy")
    }
}