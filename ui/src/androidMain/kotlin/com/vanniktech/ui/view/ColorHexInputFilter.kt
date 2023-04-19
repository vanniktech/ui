package com.vanniktech.ui.view

import android.text.InputFilter
import android.text.Spanned
import com.vanniktech.ui.Color
import com.vanniktech.ui.Color.Companion.HEX_DIGITS

internal object ColorHexInputFilter : InputFilter {
  override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
    val new = source.subSequence(start, end).toString()
    val value = dest.toString() + new

    // To support copy and paste. This is part I.
    val color = Color.fromHexOrNull(new)
    if (color != null) {
      return color.hexString()
    }

    if (value.all { HEX_DIGITS.contains(it) } && value.length <= 6) {
      return null
    }

    return "" // Reject.
  }
}
