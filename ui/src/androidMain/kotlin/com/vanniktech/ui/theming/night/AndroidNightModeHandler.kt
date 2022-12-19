package com.vanniktech.ui.theming.night

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

class AndroidNightModeHandler(
  private val context: Context,
) : NightModeHandler {
  override fun isNightMode(): Boolean {
    val isNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    val isNotOptedOut = AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO
    return isNightMode && isNotOptedOut
  }

  override fun updateBehavior(nightModeBehavior: NightModeBehavior) {
    AppCompatDelegate.setDefaultNightMode(nightModeBehavior.value)
  }
}
