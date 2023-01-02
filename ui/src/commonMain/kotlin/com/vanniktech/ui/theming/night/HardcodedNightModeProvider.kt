package com.vanniktech.ui.theming.night

class HardcodedNightModeProvider(
  private var isNightMode: Boolean,
) : NightModeProvider {
  override fun isNightMode() = isNightMode
}
