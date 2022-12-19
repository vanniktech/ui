package com.vanniktech.ui.theming.night

import kotlin.test.Test
import kotlin.test.assertEquals

class NightModeBehaviorTest {
  @Test fun values() {
    assertEquals(
      expected = listOf(1, 2, -1),
      actual = NightModeBehavior.values().map { it.value },
    )
  }

  @Test fun from() {
    assertEquals(expected = NightModeBehavior.FOLLOW_SYSTEM, actual = NightModeBehavior.fromOrNull(-1))
    assertEquals(expected = NightModeBehavior.NO, actual = NightModeBehavior.fromOrNull(1))
    assertEquals(expected = NightModeBehavior.YES, actual = NightModeBehavior.fromOrNull(2))
    assertEquals(expected = null, actual = NightModeBehavior.fromOrNull(3))
  }
}
