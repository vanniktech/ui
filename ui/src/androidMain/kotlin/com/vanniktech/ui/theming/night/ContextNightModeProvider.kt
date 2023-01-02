package com.vanniktech.ui.theming.night

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

class ContextNightModeProvider(
  private val context: Context,
) : NightModeProvider {
  override fun isNightMode() = when (AppCompatDelegate.getDefaultNightMode()) {
    AppCompatDelegate.MODE_NIGHT_YES -> true
    AppCompatDelegate.MODE_NIGHT_NO -> false
    else -> context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
  }
}
