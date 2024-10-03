package com.example.diceroll.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

abstract class BaseAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "$this.javaClass.simpleName onCreate")
    }
    override fun onStart() {
        super.onStart()
        Log.v(TAG, "$this.javaClass.simpleName onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "$this.javaClass.simpleName onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "$this.javaClass.simpleName onDestroy")
    }
}