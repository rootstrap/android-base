package com.rootstrap.android.ui.sample

import android.databinding.ViewDataBinding
import android.widget.Toast
import com.rootstrap.android.App
import com.rootstrap.android.databinding.ActivityLogBinding
import com.rootstrap.android.ui.base.BaseViewModel

/**
 * @author Amaury Ricardo Miranda 2019
 * */

class LogViewModel : BaseViewModel(), SampleViewDeclaration {

    //ALL STRINGS ARE FOR TEST

    private var bindingView: ActivityLogBinding? = null

    override fun addBindingView(bindingView: ViewDataBinding?) {
        this.bindingView = bindingView as ActivityLogBinding
        this.bindingView!!.viewModel = this
        this.bindingView!!.step = STEPS.LOGIN
    }

    override fun logIn() {
        Toast.makeText(App.mE.applicationContext, "Login action", Toast.LENGTH_LONG).show()
    }

    override fun signUp() {
        Toast.makeText(App.mE.applicationContext, "Login action", Toast.LENGTH_LONG).show()
    }

    override fun goToSignUp() {
        updateStep(STEPS.SING_UP)
    }

    override fun goBack() {
        updateStep(bindingView!!.step!!.previous)
    }

    override fun updateStep(currentStep: STEPS) {
        bindingView!!.step = currentStep
        bindingView!!.notifyChange()
    }

    override fun checkRepeatedPassword() {
        Toast.makeText(App.mE.applicationContext, "checkRepeatedPassword action", Toast.LENGTH_LONG).show()
    }

    override fun checkEmail() {
        Toast.makeText(App.mE.applicationContext, "checkEmail action", Toast.LENGTH_LONG).show()
    }

    override fun getIcon() : String {
        return "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
    }
}

enum class STEPS(val code: Int, val title: String, val previous: STEPS) {
    LOGIN(0, "Login", LOGIN),
    SING_UP(1, "Sing Up", LOGIN),
    CHECK_CODE(2, "Check Code", SING_UP)
}
