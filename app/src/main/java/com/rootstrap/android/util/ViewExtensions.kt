package com.rootstrap.android.util

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
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
 * Using Glide to load an image
 * in case you change Glide by other library like Picasso or Fresco
 * just change this extension
 * @param uri [Uri] Image URI
 * */
fun ImageView.loadUri(uri: Uri) {
    Glide.with(context)
        .load(uri)
        .into(this)
}

/**
 * Layout Inflater for Recycler View adapters
 * @param layout [Int] Layout xlm
 * */
fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)

/**
 * Add more extensions in case you need it
 * */

