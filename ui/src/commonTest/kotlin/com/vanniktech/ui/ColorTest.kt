package com.vanniktech.ui

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ColorTest {
  @Test fun serialization() {
    val color = Color.UNTINTED
    val json = "-10289408"

    assertEquals(expected = json, actual = Json.encodeToString(Color.serializer(), color))
    assertEquals(expected = color, actual = Json.decodeFromString(Color.serializer(), json))
  }

  @Test fun untinted() {
    assertEquals(expected = "#62FF00", actual = Color.UNTINTED.toString())
  }

  @Test fun copyWhite() {
    assertEquals(expected = "#00FFFFFF", actual = Color.WHITE.copy(alpha = 0).toString())
    assertEquals(expected = "#00FFFFFF", actual = Color.WHITE.copy(alpha = 0f).toString())
    assertEquals(expected = "#80FFFFFF", actual = Color.WHITE.copy(alpha = 128).toString())
    assertEquals(expected = "#80FFFFFF", actual = Color.WHITE.copy(alpha = 0.5f).toString())
    assertEquals(expected = "#B3FFFFFF", actual = Color.WHITE.copy(alpha = 0.7f).toString())
    assertEquals(expected = "#FFFFFF", actual = Color.WHITE.copy(alpha = 1f).toString())

    assertEquals(expected = "#00FFFF", actual = Color.WHITE.copy(red = 0).toString())
    assertEquals(expected = "#00FFFF", actual = Color.WHITE.copy(red = 0f).toString())
    assertEquals(expected = "#80FFFF", actual = Color.WHITE.copy(red = 128).toString())
    assertEquals(expected = "#80FFFF", actual = Color.WHITE.copy(red = 0.5f).toString())
    assertEquals(expected = "#B3FFFF", actual = Color.WHITE.copy(red = 0.7f).toString())
    assertEquals(expected = "#FFFFFF", actual = Color.WHITE.copy(red = 1f).toString())

    assertEquals(expected = "#FF00FF", actual = Color.WHITE.copy(green = 0).toString())
    assertEquals(expected = "#FF00FF", actual = Color.WHITE.copy(green = 0f).toString())
    assertEquals(expected = "#FF80FF", actual = Color.WHITE.copy(green = 128).toString())
    assertEquals(expected = "#FF80FF", actual = Color.WHITE.copy(green = 0.5f).toString())
    assertEquals(expected = "#FFB3FF", actual = Color.WHITE.copy(green = 0.7f).toString())
    assertEquals(expected = "#FFFFFF", actual = Color.WHITE.copy(green = 1f).toString())

    assertEquals(expected = "#FFFF00", actual = Color.WHITE.copy(blue = 0).toString())
    assertEquals(expected = "#FFFF00", actual = Color.WHITE.copy(blue = 0f).toString())
    assertEquals(expected = "#FFFF80", actual = Color.WHITE.copy(blue = 128).toString())
    assertEquals(expected = "#FFFF80", actual = Color.WHITE.copy(blue = 0.5f).toString())
    assertEquals(expected = "#FFFFB3", actual = Color.WHITE.copy(blue = 0.7f).toString())
    assertEquals(expected = "#FFFFFF", actual = Color.WHITE.copy(blue = 1f).toString())
  }

  @Test fun htmlRgbaString() {
    assertEquals(expected = "rgba(0, 0, 0, 0)", actual = 0x0.color.htmlRgbaString())
    assertEquals(expected = "rgba(0, 15, 255, 0)", actual = 0xFFF.color.htmlRgbaString())
    assertEquals(expected = "rgba(255, 255, 255, 0)", actual = 0xFFFFFF.color.htmlRgbaString())
    assertEquals(expected = "rgba(36, 36, 36, 255)", actual = 0xFF242424.color.htmlRgbaString())
  }

  @Test fun string() {
    assertEquals(expected = "#00000000", actual = 0x0.color.toString())
    assertEquals(expected = "#00000FFF", actual = 0xFFF.color.toString())
    assertEquals(expected = "#00FFFFFF", actual = 0xFFFFFF.color.toString())
    assertEquals(expected = "#242424", actual = 0xFF242424.color.toString())
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

  @Test fun fromHexOrNull() {
    assertEquals(expected = Color.UNTINTED, actual = Color.fromHexOrNull("#FF62FF00"))
    assertEquals(expected = Color(0xFF456456.toInt()), actual = Color.fromHexOrNull("#456"))
    assertEquals(expected = Color(0x12341234), actual = Color.fromHexOrNull("#1234"))
    assertEquals(expected = Color(0xFF123456.toInt()), actual = Color.fromHexOrNull("#123456"))
    assertEquals(expected = Color(0xFF123456.toInt()), actual = Color.fromHexOrNull("123456"))
    assertEquals(expected = null, actual = Color.fromHexOrNull("S"))
  }

  @Test fun brighten() {
    assertEquals(expected = 0xFF4ECC00.color, actual = Color.UNTINTED.brighten(0.8f))
    assertEquals(expected = 0xFF58E500.color, actual = Color.UNTINTED.brighten(0.9f))
    assertEquals(expected = Color.UNTINTED, actual = Color.UNTINTED.brighten(1f))
    assertEquals(expected = 0xFF6BFF00.color, actual = Color.UNTINTED.brighten(1.1f))
    assertEquals(expected = 0xFF75FF00.color, actual = Color.UNTINTED.brighten(1.2f))
  }
}
