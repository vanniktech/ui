package com.vanniktech.ui

import kotlin.test.Test
import kotlin.test.assertEquals

class ArgbEvaluatorTest {
  @Test fun evaluate() {
    TEST_VALUES.forEach {
      assertEquals(it.expected, ArgbEvaluator.evaluate(it.fraction, it.start, it.end))
    }
  }
}
