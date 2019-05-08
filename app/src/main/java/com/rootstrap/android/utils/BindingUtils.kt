package com.rootstrap.android.utils

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File

/**
 * @author Amaury Ricardo Miranda 2019
 * */

@BindingAdapter(value = ["app:imageUrl", "app:placeholderResource", "app:errorResource"], requireAll = false)
fun setLoadImage(
    imageView: ImageView,
    imageUrl: String,
    placeholderResource: Int? = null,
    errorResource: Int? = null
) {
    loadImage(Picasso.get().load(imageUrl), imageView, placeholderResource, errorResource)
}

@BindingAdapter(value = ["app:imageUri", "app:placeholderResource", "app:errorResource"], requireAll = false)
fun setLoadImage(
    imageView: ImageView,
    imageUri: Uri,
    placeholderResource: Int? = null,
    errorResource: Int? = null
) {
    loadImage(Picasso.get().load(imageUri), imageView, placeholderResource, errorResource)
}

@BindingAdapter(value = ["app:resourceId", "app:placeholderResource", "app:errorResource"], requireAll = false)
fun setLoadImage(
    imageView: ImageView,
    resourceId: Int,
    placeholderResource: Int? = null,
    errorResource: Int? = null
) {
    loadImage(Picasso.get().load(resourceId), imageView, placeholderResource, errorResource)
}

@BindingAdapter(value = ["app:imageFile", "app:placeholderResource", "app:errorResource"], requireAll = false)
fun setLoadImage(
    imageView: ImageView,
    imageFile: File,
    placeholderResource: Int? = null,
    errorResource: Int? = null
) {
    loadImage(Picasso.get().load(imageFile), imageView, placeholderResource, errorResource)
}

private fun loadImage(
    piccasoCreator: RequestCreator?,
    imageView: ImageView,
    placeholderResource: Int?,
    errorResource: Int?
) {
    if (placeholderResource != null) piccasoCreator!!.placeholder(placeholderResource)
    if (errorResource != null) piccasoCreator!!.error(errorResource)
    piccasoCreator!!.into(imageView)
}
