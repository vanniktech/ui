package com.vanniktech.ui

import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.appcompat.app.ActionBar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.MaterialShapeDrawable

fun View.backgroundColor() = ((background as? ColorDrawable)?.color ?: (background as? MaterialShapeDrawable)?.resolvedTintColor)?.color

fun View.click() {
  if (isEnabled) {
    performClick()
  }
}

fun ExtendedFloatingActionButton.show(isVisible: Boolean) = when (isVisible) {
  true -> show()
  else -> hide()
}

fun FloatingActionButton.show(isVisible: Boolean) = when (isVisible) {
  true -> show()
  else -> hide()
}

fun View.parentViewGroup() = parent as ViewGroup
fun ViewGroup.children(): List<View> = (0 until childCount).map { getChildAt(it) }

fun View.visibleGone(isVisible: Boolean) = visibleElse(isVisible, GONE)
fun View.visibleInvisible(isVisible: Boolean) = visibleElse(isVisible, INVISIBLE)
fun View.visibleElse(isVisible: Boolean, other: Int) {
  visibility = when (isVisible) {
    true -> VISIBLE
    else -> other
  }
}

fun ActionBar.setText(string: CharSequence?) {
  setDisplayHomeAsUpEnabled(true)
  setDisplayShowTitleEnabled(true)
  setDisplayUseLogoEnabled(false)
  title = string
}

@Px fun TextView.setTextSizeRes(@DimenRes value: Int, factor: Float = 1.0f): Float {
  val px = resources.getDimension(value) * factor
  setTextSize(TypedValue.COMPLEX_UNIT_PX, px)
  return px
}
