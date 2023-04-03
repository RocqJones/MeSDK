package com.rocqjones.me_logic.utils

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.rocqjones.me_logic.R

/**
 * This will be used instead of normal boring Toast
 */
class ToastUtils(activity : Activity) {

    private var snackBar: Snackbar? = null
    private var activityContext: Activity? = null

    init {
        this.activityContext = activity
    }

    fun showSnackBar(message: String) {
        try {
            val contentView = activityContext!!.findViewById<View>(android.R.id.content)
            snackBar = Snackbar.make(contentView, message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(activityContext!!.resources.getColor(R.color.blue))
            snackBar!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showSnackBarError(message: String) {
        try {
            val contentView = activityContext!!.findViewById<View>(android.R.id.content)
            snackBar = Snackbar.make(contentView, message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(activityContext!!.resources.getColor(R.color.red))
            snackBar!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}