package com.vanniktech.ui

import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toDrawable

fun View.setBackgroundColor(color: Color) = setBackgroundColor(color.argb)
fun TextView.setTextColor(color: Color) = setTextColor(color.argb)
fun CardView.setCardBackgroundColor(color: Color) = setCardBackgroundColor(color.argb)
fun Paint.setColor(color: Color) {
  this.color = color.argb
}

fun ColorDrawable(color: Color): ColorDrawable = color.argb.toDrawable()

fun Color.colorStateList() = ColorStateList.valueOf(argb)

fun Color.coloredText(text: CharSequence?) = when {
  text.isNullOrBlank() -> null
  else -> SpannableString(text).apply {
    setSpan(ForegroundColorSpan(argb), 0, text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
  }
}

fun colorStateList(state: Int, on: Color, off: Color) = ColorStateList(
  arrayOf(intArrayOf(state), intArrayOf(-state)),
  intArrayOf(on.argb, off.argb),
)

fun NotificationCompat.Builder.setColor(color: Color) = setColor(color.argb)

fun TextView.setTextSizeSp(sp: Float) {
  setTextSize(TypedValue.COMPLEX_UNIT_SP, sp)
}

fun ImageView.tintIcon(color: Color, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) = setColorFilter(color.argb, mode)
