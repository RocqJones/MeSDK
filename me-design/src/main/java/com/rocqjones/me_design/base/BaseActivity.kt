package com.rocqjones.me_design.base

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.rocqjones.me_logic.utils.ToastUtils

/**
 * This will be the base class of our Design library where we'll be defining all our common behaviour used across our Activities.
 * We can have a lot more abstract methods, for every bit we want specific to our subclasses.
 * Example Usage: 'CustomToastUtils' demonstrates this well
 */
abstract class BaseActivity : ComponentActivity() {

    lateinit var activityContext: Activity
    lateinit var toastUtils: ToastUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        try {
            activityContext = activityContext()
            toastUtils = ToastUtils(activityContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected abstract fun activityContext(): Activity
}