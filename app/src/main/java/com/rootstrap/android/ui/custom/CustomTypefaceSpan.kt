package com.rootstrap.android.ui.custom

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

/**
 * Allows to apply a custom font in an spannableString
 *
 * Taken from https://stackoverflow.com/questions/10675070/multiple-typeface-in-single-textview
 * with slightly modifications
 */
class CustomTypefaceSpan(private val newType: Typeface) : TypefaceSpan("") {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    companion object {

        private const val ITALIC_SKEW_FACTOR = -0.25F

        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = ITALIC_SKEW_FACTOR
            }
            paint.typeface = tf
        }
    }
}
