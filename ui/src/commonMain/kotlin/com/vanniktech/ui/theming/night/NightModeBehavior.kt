package com.vanniktech.ui.theming.night

enum class NightModeBehavior(
  /** This is the Android constant that we can use to set the night mode. */
  val value: Int,
) {
  NO(value = 1),
  YES(value = 2),
  FOLLOW_SYSTEM(-1),
  ;

  companion object {
    fun list() = values().toList()
    fun fromOrNull(value: Int?) = values().firstOrNull { it.value == value }
  }
}
