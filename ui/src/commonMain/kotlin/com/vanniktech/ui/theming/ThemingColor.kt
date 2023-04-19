package com.vanniktech.ui.theming

import com.vanniktech.ui.Color

data class ThemingColor(
  val light: Color,
  val dark: Color,
) {
  fun with(alpha: Float) = ThemingColor(
    light = light.copy(alpha = alpha),
    dark = dark.copy(alpha = alpha),
  )

  fun mapped(isNight: Boolean) = when (isNight) {
    true -> dark
    else -> light
  }

  override fun toString() = "Light: $light, Dark: $dark"

  companion object {
    fun single(color: Color) = ThemingColor(
      light = color,
      dark = color,
    )
  }
}
