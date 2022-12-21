package com.vanniktech.ui

import android.os.Build.VERSION.SDK_INT
import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat

fun Window.themeWindow(
  statusBarColor: Color,
  navigationBarColor: Color,
  lightNavigationBars: Boolean,
  lightStatusBars: Boolean,
) {
  WindowInsetsControllerCompat(this, this.decorView).apply {
    isAppearanceLightNavigationBars = lightNavigationBars
    isAppearanceLightStatusBars = lightStatusBars
  }

  setStatusBarColor(statusBarColor)
  setNavigationBarColor(navigationBarColor)
}

private fun Window.setStatusBarColor(color: Color) {
  statusBarColor = when {
    SDK_INT >= 23 -> color.argb
    else -> when (color.shouldUseBlackFont()) {
      true -> Color.BLACK
      else -> color
    }.argb
  }
}

private fun Window.setNavigationBarColor(color: Color) {
  navigationBarColor = when {
    SDK_INT >= 26 -> color.argb
    else -> when (color.shouldUseBlackFont()) {
      true -> Color.BLACK
      else -> color
    }.argb
  }
}
