package com.rootstrap.android.ui.base

import androidx.fragment.app.Fragment
import com.rootstrap.android.util.LoadingDialogUtil

open class BaseFragment : Fragment(), BaseView {

    override fun showProgress() {
        LoadingDialogUtil.showProgress(requireContext())
    }

    override fun hideProgress() {
        LoadingDialogUtil.hideProgress()
    }

    override fun showError(message: String?) {
        LoadingDialogUtil.showError(message, requireContext())
    }
}
