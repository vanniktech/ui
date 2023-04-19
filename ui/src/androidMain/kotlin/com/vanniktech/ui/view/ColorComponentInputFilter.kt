package com.vanniktech.ui.view

import android.text.InputFilter
import android.text.Spanned
import com.vanniktech.ui.COLOR_COMPONENT_RANGE

internal object ColorComponentInputFilter : InputFilter {
  override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
    val value = dest.toString() + source.subSequence(start, end).toString()

    if (value.toIntOrNull() in COLOR_COMPONENT_RANGE) {
      return null
    }

    return "" // Reject.
  }
}
