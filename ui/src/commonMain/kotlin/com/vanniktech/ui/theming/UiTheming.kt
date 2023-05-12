package com.vanniktech.ui.theming

import com.vanniktech.ui.Color

interface UiTheming {
  val isNight: Boolean

  fun colorPrimary(): Color
  val colorPrimary: ThemingColor

  fun colorOnPrimary(): Color
  val colorOnPrimary: ThemingColor

  fun colorSecondary(): Color
  val colorSecondary: ThemingColor

  fun colorOnSecondary(): Color
  val colorOnSecondary: ThemingColor

  fun colorBackgroundPrimary(): Color
  val colorBackgroundPrimary: ThemingColor

  fun colorBackgroundSecondary(): Color
  val colorBackgroundSecondary: ThemingColor

  fun colorBackgroundTertiary(): Color
  val colorBackgroundTertiary: ThemingColor

  fun colorTopNavigation(): Color
  val colorTopNavigation: ThemingColor

  fun colorBottomNavigation(): Color
  val colorBottomNavigation: ThemingColor

  fun colorTextPrimary(): Color
  val colorTextPrimary: ThemingColor

  fun colorTextSecondary(): Color
  val colorTextSecondary: ThemingColor

  fun colorDivider(): Color
  val colorDivider: ThemingColor

  fun colorRipple(): Color
  val colorRipple: ThemingColor

  fun colorError(): Color
  val colorError: ThemingColor

  fun colorWarning(): Color
  val colorWarning: ThemingColor

  fun colorSuccess(): Color
  val colorSuccess: ThemingColor
}
