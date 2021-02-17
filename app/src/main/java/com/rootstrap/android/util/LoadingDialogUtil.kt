package com.rootstrap.android.util

import android.app.AlertDialog
import android.content.Context
import com.rootstrap.android.R
import com.rootstrap.android.ui.custom.LoadingDialog

object LoadingDialogUtil {

    private var loadingDialog: LoadingDialog? = null

    fun showProgress(context: Context) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(context, null)
        }

        loadingDialog?.show()
    }

    fun hideProgress() {
        loadingDialog?.run { dismiss() }
    }

    fun showError(message: String?, context: Context) {
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(context.getString(R.string.error))

            val showMessage = if (message.isNullOrEmpty())
                context.getString(R.string.generic_error)
            else message

            setMessage(showMessage)

            setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }
            val dialog: AlertDialog = create()
            dialog.show()
        }
    }
}
