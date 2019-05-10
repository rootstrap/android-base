package com.rootstrap.android.utils

import java.util.regex.Pattern
/**
 * @author Amaury Ricardo Miranda 2019
 * */

/**
 * @return Boolean : true if is email
 * */
fun String.isEmail(): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * @param regex Regex string function to validate with Pattern.matches
 * @return Boolean : true if pattern match
 * */
fun String.validate(regex: String): Boolean = Pattern.matches(regex, this)

/**
 * Add more validators functions
 * */
