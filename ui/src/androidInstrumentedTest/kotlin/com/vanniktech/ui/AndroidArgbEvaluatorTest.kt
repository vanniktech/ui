package com.vanniktech.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) class AndroidArgbEvaluatorTest {
  @Test fun evaluate() {
    TEST_VALUES.forEach {
      assertEquals(it.expected.argb, android.animation.ArgbEvaluator().evaluate(it.fraction, it.start.argb, it.end.argb))
    }
  }
}
