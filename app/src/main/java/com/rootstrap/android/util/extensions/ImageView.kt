package com.rootstrap.android.util.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

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
* Add more extensions in case you need it
* */
