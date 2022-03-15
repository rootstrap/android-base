package com.rootstrap.android.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rootstrap.android.R
import com.rootstrap.android.util.LoadingDialogUtil
import com.rootstrap.android.util.NetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    fun openActivity(activity: Class<*>, clearTask: Boolean = false, extras: Bundle? = null) {
        requireActivity().startActivity(
            Intent(requireActivity(), activity).also {
                if (clearTask) {
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                extras?.let { bundle -> it.putExtras(bundle) }
            }
        )
        if (clearTask) {
            requireActivity().finish()
        }
    }

    fun observeNetwork(baseViewModel: BaseViewModel) {
        baseViewModel.networkState.observe(this, {
            when (it) {
                NetworkState.loading -> showProgress()
                NetworkState.idle -> hideProgress()
                else -> showError(baseViewModel.error ?: getString(R.string.default_error))
            }
        })
    }

    fun navigateTo(routeOrAction: Int, bundle: Bundle? = null) {
        requireActivity().takeIf { it is BaseNavActivity }?.let {
            it as BaseNavActivity
            it.navigateTo(routeOrAction, bundle)
        }
    }
}
