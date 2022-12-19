package com.vanniktech.ui

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ColorTest {
  @Test fun serialize() {
    assertEquals(expected = "-10289408", actual = Json.encodeToString(Color.serializer(), Color.UNTINTED))
  }

  @Test fun deserialize() {
    assertEquals(expected = Color.UNTINTED, actual = Json.decodeFromString(Color.serializer(), "-10289408"))
  }

  @Test fun untinted() {
    assertEquals(expected = "#FF62FF00", actual = Color.UNTINTED.toString())
  }

  @Test fun htmlRgbaString() {
    assertEquals(expected = "rgba(0, 0, 0, 0)", actual = 0x0.color.htmlRgbaString())
    assertEquals(expected = "rgba(0, 15, 255, 0)", actual = 0xFFF.color.htmlRgbaString())
    assertEquals(expected = "rgba(255, 255, 255, 0)", actual = 0xFFFFFF.color.htmlRgbaString())
    assertEquals(expected = "rgba(36, 36, 36, 255)", actual = 0xFF242424.color.htmlRgbaString())
  }

  @Test fun string() {
    assertEquals(expected = "#00000000", actual = 0x0.color.toString())
    assertEquals(expected = "#FFF", actual = 0xFFF.color.toString())
    assertEquals(expected = "#FFFFFF", actual = 0xFFFFFF.color.toString())
    assertEquals(expected = "#FF242424", actual = 0xFF242424.color.toString())
  }

  @Test fun with() {
    // Alpha present.
    assertEquals(expected = "#66181818", actual = 0xCC181818.color.with(alpha = 102).toString())
    assertEquals(expected = "#66181818", actual = 0xCC181818.color.with(alpha = 0.4f).toString())

    // No alpha.
    assertEquals(expected = "#66181818", actual = 0x181818.color.with(alpha = 102).toString())
    assertEquals(expected = "#66181818", actual = 0x181818.color.with(alpha = 0.4f).toString())

    // Underflow.
    assertEquals(expected = "#181818", actual = 0x181818.color.with(alpha = -1).toString())
    assertEquals(expected = "#181818", actual = 0x181818.color.with(alpha = -0.1f).toString())

    // Overflow.
    assertEquals(expected = "#FF181818", actual = 0x181818.color.with(alpha = 256).toString())
    assertEquals(expected = "#FF181818", actual = 0x181818.color.with(alpha = 1.1f).toString())
  }

  @Test fun shouldUseBlackFont() {
    mapOf(
      0xFF0000.color to false,
      0x00FF00.color to false,
      0x0000FF.color to false,
      0x000000.color to false,
      0xFFFFFF.color to true,
    ).forEach { (color, boolean) ->
      assertEquals(boolean, color.shouldUseBlackFont())
    }
  }
}
