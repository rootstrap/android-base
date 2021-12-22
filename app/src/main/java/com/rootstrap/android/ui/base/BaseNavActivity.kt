package com.rootstrap.android.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.rootstrap.android.R

open class BaseNavActivity : BaseActivity() {

    open var navGraph: Int? = null
    private lateinit var navController: NavController
    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)?.childFragmentManager!!.fragments[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_nav)
        navGraph?.let { navigation ->
            navController = findNavController(R.id.fragment_container).also {
                it.setGraph(navigation)
            }
        }
    }

    fun navigateTo(routeOrAction: Int, bundle: Bundle? = null) {
        navController.navigate(routeOrAction, bundle)
    }

    override fun onBackPressed() {
        navigateBackFromFragment(currentFragment)
    }

    private fun navigateBackFromFragment(fragment: Fragment, toDestination: Int? = null) {
        toDestination?.let {
            NavHostFragment.findNavController(fragment).popBackStack(it, false)
        } ?: run {
            if (!NavHostFragment.findNavController(fragment).popBackStack()) {
                super.onBackPressed()
            }
        }
    }
}
