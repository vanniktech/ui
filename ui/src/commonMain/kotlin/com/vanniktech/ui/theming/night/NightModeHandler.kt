package com.vanniktech.ui.theming.night

interface NightModeHandler {
  /** Returns true if we are this current moment using the night mode. */
  fun isNightMode(): Boolean

  fun updateBehavior(nightModeBehavior: NightModeBehavior)
}
