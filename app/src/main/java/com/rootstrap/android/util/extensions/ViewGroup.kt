package com.rootstrap.android.util.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Layout Inflater for Recycler View adapters
 * @param layout [Int] Layout xlm
 * */
fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)

/**
 * Add more extensions in case you need it
 * */
