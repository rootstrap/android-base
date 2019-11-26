package com.rootstrap.android.util.extensions

import android.widget.EditText
import java.util.regex.Pattern

/**
 * [EditText] value
 * */
fun EditText.value() = text.toString().trim()

/**
 * Validate [EditText] with pattern
 * @param pattern [String] Pattern
 * */
fun EditText.validate(pattern: String): Boolean =
    Pattern.compile(pattern, Pattern.CASE_INSENSITIVE)
        .matcher(value())
        .matches()

/**
 * Validate [EditText] doesn't have a null value
 * */
fun EditText.isNotEmpty() = value().isNotEmpty()

/**
 * Add more extensions in case you need it
 * */
