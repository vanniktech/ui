package com.vanniktech.ui.theming.night

import androidx.appcompat.app.AppCompatDelegate

object AndroidNightModeBehaviorHandler : NightModeBehaviorHandler {
  override fun updateBehavior(nightModeBehavior: NightModeBehavior) {
    AppCompatDelegate.setDefaultNightMode(nightModeBehavior.value)
  }
}
