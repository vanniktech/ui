package com.vanniktech.ui.theming.night

interface NightModeProvider {
  /** Returns true if we are this current moment using the night mode. */
  fun isNightMode(): Boolean
}
