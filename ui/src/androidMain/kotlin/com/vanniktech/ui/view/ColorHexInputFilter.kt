package com.vanniktech.ui.view

import android.text.InputFilter
import android.text.Spanned
import com.vanniktech.ui.Color
import com.vanniktech.ui.Color.Companion.HEX_DIGITS
import com.vanniktech.ui.HEX_PREFIX

internal class ColorHexInputFilter(
  private val supportsAlpha: Boolean,
) : InputFilter {
  override fun filter(
    source: CharSequence,
    start: Int,
    end: Int,
    dest: Spanned,
    dstart: Int,
    dend: Int,
  ): CharSequence? {
    val dest1 = dest.subSequence(0, dstart).toString()
    val new = source.subSequence(start, end)
    val dest2 = dest.subSequence(dend, dest.length)
    val value = dest1 + new + dest2

    // To support copy and paste. This is part I.
    val color = inferColor(value, supportsAlpha)
    if (color != null) {
      if (value.startsWith(HEX_PREFIX)) {
        return value.removePrefix(HEX_PREFIX)
      }

      return null // Accept.
    }

    val maxLength = when (supportsAlpha) {
      true -> 8
      else -> 6
    }

    if (value.all { HEX_DIGITS.contains(it) } && value.length <= maxLength) {
      return null // Accept.
    }

    return "" // Reject.
  }
}

internal fun inferColor(string: String, supportsAlpha: Boolean): Color? {
  val digits = setOf(
    3,
    4.takeIf { supportsAlpha },
    6,
    8.takeIf { supportsAlpha },
  )
  val cleanedString = string.removePrefix(HEX_PREFIX)
  return Color.fromHexOrNull(cleanedString).takeIf { digits.contains(cleanedString.length) }
}
