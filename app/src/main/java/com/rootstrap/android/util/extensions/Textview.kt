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

/**
 * Sets the first occurrence of the keyword string in this TextView's text as clickable and attach the
 * given OnClickListener function to it.
 *
 * Additionally, it tints the keyword using the app's colorPrimary color and adds an underline to it
 * so it looks and behaves like an Hyperlink.
 *
 * This behavior can be customized with the keywordColor and underline params and you can also change
 * the keyword's typeFace with the typeFace param, allowing you to make it bold or apply
 * other kinds of effects.
 *
 * You can add multiple clickable keywords by just calling this method as many times as you need in
 * the same textview.
 *
 * The next example illustrates how to add a link to open a web browser:
 *```
 * mySuggestionText.text = "For more information go to the admin web"
 * mySuggestionText.setClickableKeyword("admin web") {
 *   // Get admin url and open the admin web via an intent
 * }
 *```
 */
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

/**
 * changes the color of the first occurrence of the keyword string in this TextView's text.
 *
 * It accept a color resource as a second parameter and by default it uses the app's colorPrimary color.
 */
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
