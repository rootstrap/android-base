package com.rootstrap.android.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.rootstrap.android.R
import com.rootstrap.android.ui.custom.LoadingDialog

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), BaseView {

    private var loadingDialog: LoadingDialog? = null

    override fun showProgress() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this, null)
        }

        loadingDialog!!.show()
    }

    override fun hideProgress() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
        }
    }

    override fun showError(message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.error))

        when (message) {
            "" -> builder.setMessage(getString(R.string.generic_error))
            null -> builder.setMessage(getString(R.string.generic_error))
            else -> builder.setMessage(message)
        }

        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.cancel()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    protected fun startActivityClearTask(activity: Activity) {
        val intent = Intent(this, activity.javaClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
