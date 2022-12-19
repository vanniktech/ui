package com.vanniktech.ui.theming.night

class HardcodedNightModeHandler(
  private var isNightMode: Boolean,
) : NightModeHandler {
  override fun isNightMode() = isNightMode

  override fun updateBehavior(nightModeBehavior: NightModeBehavior) {
    // No-op.
  }
}
