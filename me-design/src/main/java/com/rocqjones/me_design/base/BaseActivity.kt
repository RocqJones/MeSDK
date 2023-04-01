package com.rocqjones.me_design.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import com.rocqjones.me_logic.utils.CustomToastUtils

/**
 * This will be the base class of our Design library where we'll be defining all our common behaviour used across our Activities.
 * We can have a lot more abstract methods, for every bit we want specific to our subclasses.
 * Example Usage: 'CustomToastUtils' demonstrates this well
 */
abstract class BaseActivity : ComponentActivity() {

    lateinit var toastUtils : CustomToastUtils

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        // init
        toastUtils = CustomToastUtils(this)
    }
}