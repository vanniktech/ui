package com.vanniktech.ui

internal data class ArgbEvaluatorValues(
  val expected: Color,
  val fraction: Float,
  val start: Color,
  val end: Color,
)

internal val TEST_VALUES = listOf(
  ArgbEvaluatorValues(expected = 0xFF00FF00.color, fraction = 0.0f, start = 0xFF00FF00.color, end = 0xFF00FF00.color),
  ArgbEvaluatorValues(expected = 0xFFFF8080.color, fraction = 0.0f, start = 0xFFFF8080.color, end = 0xFF8080FF.color),
  ArgbEvaluatorValues(expected = 0xFFCC80CC.color, fraction = 0.5f, start = 0xFFFF8080.color, end = 0xFF8080FF.color),
  ArgbEvaluatorValues(expected = 0xFF8080FF.color, fraction = 1f, start = 0xFFFF8080.color, end = 0xFF8080FF.color),
)
