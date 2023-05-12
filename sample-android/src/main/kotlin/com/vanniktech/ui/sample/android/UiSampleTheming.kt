package com.vanniktech.ui.sample.android

import com.vanniktech.ui.Color
import com.vanniktech.ui.color
import com.vanniktech.ui.theming.ThemingColor
import com.vanniktech.ui.theming.UiTheming

data class UiSampleTheming(
  override val isNight: Boolean,
  private val tint: Color?,
  private val secondaryTint: Color? = tint,
) : UiTheming {
  override fun colorPrimary() = colorPrimary.mapped(isNight)
  override val colorPrimary = when (tint) {
    null -> ThemingColor(light = 0xFF363636.color, dark = Color.WHITE)
    else -> ThemingColor.single(tint)
  }

  override fun colorSecondary() = colorSecondary.mapped(isNight)
  override val colorSecondary = when (secondaryTint) {
    null -> ThemingColor(light = 0xFF363636.color, dark = Color.WHITE)
    else -> ThemingColor.single(secondaryTint)
  }

  override fun colorBackgroundPrimary() = colorBackgroundPrimary.mapped(isNight)
  override val colorBackgroundPrimary get() = ThemingColor(light = Color.WHITE, dark = 0xFF363636.color)

  override fun colorBackgroundSecondary() = colorBackgroundSecondary.mapped(isNight)
  override val colorBackgroundSecondary get() = ThemingColor(light = 0xFFF0F0F0.color, dark = 0xFF202020.color)

  override fun colorBackgroundTertiary() = colorBackgroundTertiary.mapped(isNight)
  override val colorBackgroundTertiary get() = ThemingColor(light = 0xFFE0E0E0.color, dark = 0xFF121212.color)

  override fun colorDivider() = colorDivider.mapped(isNight)
  override val colorDivider get() = ThemingColor(light = 0xFFDCDCDC.color, dark = 0xFF555555.color)

  override fun colorOnPrimary() = colorOnPrimary.mapped(isNight)
  override val colorOnPrimary get() = if (colorPrimary.mapped(isNight).shouldUseBlackFont()) ThemingColor.single(Color.BLACK) else ThemingColor.single(Color.WHITE)

  override fun colorOnSecondary() = colorOnSecondary.mapped(isNight)
  override val colorOnSecondary get() = if (colorSecondary.mapped(isNight).shouldUseBlackFont()) ThemingColor.single(Color.BLACK) else ThemingColor.single(Color.WHITE)

  override fun colorText() = colorText.mapped(isNight)
  override val colorText get() = ThemingColor(light = 0xFF363636.color, dark = Color.WHITE)

  override fun colorTextSecondary() = colorTextSecondary.mapped(isNight)
  override val colorTextSecondary = colorText.with(alpha = 0.6f)

  override fun colorError() = colorError.mapped(isNight)
  override val colorError get() = ThemingColor.single(0xFFF44336.color)

  override fun colorWarning() = colorWarning.mapped(isNight)
  override val colorWarning get() = ThemingColor.single(0xFFFFEB3B.color)

  override fun colorSuccess() = colorSuccess.mapped(isNight)
  override val colorSuccess get() = ThemingColor.single(0xFF4CAF50.color)

  override fun colorRipple() = colorRipple.mapped(isNight)
  override val colorRipple get() = ThemingColor(light = 0x33FFFFFF.color, dark = 0x1F000000.color)

  override fun colorTopNavigation() = colorTopNavigation.mapped(isNight)
  override val colorTopNavigation get() = ThemingColor(light = 0xFFF0F0F0.color, dark = 0xFF1E1E1E.color)

  override fun colorBottomNavigation() = colorBottomNavigation.mapped(isNight)
  override val colorBottomNavigation get() = ThemingColor(light = 0xFFF0F0F0.color, dark = 0xFF1E1E1E.color)
}
