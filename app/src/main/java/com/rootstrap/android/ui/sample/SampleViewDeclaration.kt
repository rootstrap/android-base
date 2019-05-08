package com.rootstrap.android.ui.sample

/**
 * @author Amaury Ricardo Miranda 2019
 * */

interface SampleViewDeclaration {

    fun logIn()

    fun signUp()

    fun goToSignUp()

    fun goBack()

    fun checkEmail()

    fun getIcon() : String

    fun checkRepeatedPassword()

    fun updateStep(currentStep: STEPS)

}
