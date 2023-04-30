package com.vanniktech.ui.theming

import com.vanniktech.ui.Color
import com.vanniktech.ui.color
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ThemingColorTest {
  @Test fun serialization() {
    val themingColor = ThemingColor(
      light = Color.WHITE,
      dark = Color.BLACK,
    )

    val json = "{\"light\":-1,\"dark\":-16777216}"
    assertEquals(expected = json, actual = Json.encodeToString(ThemingColor.serializer(), themingColor))
    assertEquals(expected = themingColor, actual = Json.decodeFromString(ThemingColor.serializer(), json))
  }

  @Test fun single() {
    val color = 0xFFFFFFFF.color
    val themingColor = ThemingColor(light = color, dark = color)

    assertEquals(expected = color, actual = themingColor.mapped(isNight = false))
    assertEquals(expected = color, actual = themingColor.mapped(isNight = true))
    assertEquals(expected = "Light: #FFFFFF, Dark: #FFFFFF", actual = themingColor.toString())

    val alpha = 0.4f
    assertEquals(expected = color.copy(alpha = 102), actual = themingColor.with(alpha = alpha).light)
    assertEquals(expected = color.copy(alpha = 102), actual = themingColor.with(alpha = alpha).dark)
    assertEquals(expected = "Light: #66FFFFFF, Dark: #66FFFFFF", actual = themingColor.with(alpha = alpha).toString())
  }

  @Test fun lightDark() {
    val light = 0xFFABCDEF.color
    val dark = 0xFF123456.color
    val themingColor = ThemingColor(light = light, dark = dark)

    assertEquals(expected = light, actual = themingColor.mapped(isNight = false))
    assertEquals(expected = dark, actual = themingColor.mapped(isNight = true))
    assertEquals(expected = "Light: #ABCDEF, Dark: #123456", actual = themingColor.toString())

    val alpha = 0.8f
    assertEquals(expected = light.copy(alpha = 204), actual = themingColor.with(alpha = alpha).light)
    assertEquals(expected = dark.copy(alpha = 204), actual = themingColor.with(alpha = alpha).dark)
    assertEquals(expected = "Light: #CCABCDEF, Dark: #CC123456", actual = themingColor.with(alpha = alpha).toString())
  }
}
