package com.rootstrap.android.ui.custom

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import com.rootstrap.android.R

class LoadingDialog(context: Context, cancelListener: DialogInterface.OnCancelListener?) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.view_loading_dialog)
        setCanceledOnTouchOutside(false)

        if (cancelListener != null) {
            setCancelable(true)
            setOnCancelListener(cancelListener)
        } else {
            setCancelable(false)
        }
    }
}
