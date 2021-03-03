package com.rootstrap.android.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.rootstrap.android.util.LoadingDialogUtil

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), BaseView {

    override fun showProgress() {
        LoadingDialogUtil.showProgress(this)
    }

    override fun hideProgress() {
        LoadingDialogUtil.hideProgress()
    }

    override fun showError(message: String?) {
        LoadingDialogUtil.showError(message, this)
    }

    protected fun startActivityClearTask(activity: Activity) {
        val intent = Intent(this, activity.javaClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
