package com.rootstrap.android.util.extensions

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.rootstrap.android.R
import com.rootstrap.android.ui.custom.CustomTypefaceSpan

fun TextView.setClickableKeyword(
    keyword: String,
    onClickListener: () -> Unit,
    @ColorRes keywordColor: Int = R.color.colorPrimary,
    underline: Boolean = true,
    typeFace: Typeface? = null
) {
    val span: SpannableStringBuilder = getTextAsSpannable()

    val start = text.indexOf(keyword, 0)

    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) = onClickListener()
        override fun updateDrawState(ds: TextPaint) {
            ds.color = ContextCompat.getColor(context, keywordColor)
            ds.isUnderlineText = underline
        }
    }

    typeFace?.let {
        span.setSpan(CustomTypefaceSpan(typeFace), start, start + keyword.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    }
    span.setSpan(clickableSpan, start, start + keyword.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

    if (movementMethod !is LinkMovementMethod) {
        movementMethod = LinkMovementMethod.getInstance()
    }

    text = span
}

fun TextView.setColoredKeyword(
    keyword: String,
    @ColorRes keywordColor: Int = R.color.colorPrimary
) {
    val span: SpannableStringBuilder = getTextAsSpannable()
    val start = text.indexOf(keyword, 0)
    val coloredSpan = object : CharacterStyle() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = ContextCompat.getColor(context, keywordColor)
        }
    }
    span.setSpan(coloredSpan, start, start + keyword.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    text = span
}

private fun TextView.getTextAsSpannable() =
    if (text is SpannableStringBuilder) text as SpannableStringBuilder
    else SpannableStringBuilder(text)
