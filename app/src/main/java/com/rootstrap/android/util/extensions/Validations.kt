package com.rootstrap.android.util.extensions

fun String.validate(pattern: String): Boolean {
    return pattern.toRegex().matches(this)
}

fun String.isEmail(): Boolean {
    return validate(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}
