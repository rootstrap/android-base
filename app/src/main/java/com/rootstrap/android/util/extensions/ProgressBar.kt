package com.rootstrap.android.util.extensions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import androidx.core.animation.addListener

private const val DEFAULT_ANIMATION_TIME = 500L

/**
 * Smoothly animates the progress of the progress bar to the specified value
 */
fun ProgressBar.progressTo(
    newlyProgress: Int,
    timeInMillis: Long = DEFAULT_ANIMATION_TIME,
    onEndListener: ((Animator) -> Unit)? = null
) {
    ObjectAnimator.ofInt(this, "progress", progress, newlyProgress).apply {
        duration = timeInMillis
        interpolator = DecelerateInterpolator()
        setAutoCancel(true)
        onEndListener?.let {
            addListener(onEnd = it)
        }
    }.start()
}
