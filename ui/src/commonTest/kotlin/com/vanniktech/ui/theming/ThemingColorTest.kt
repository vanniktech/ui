package com.vanniktech.ui.theming

import com.vanniktech.ui.color
import kotlin.test.Test
import kotlin.test.assertEquals

class ThemingColorTest {
  @Test fun single() {
    val color = 0xFFFFFF.color
    val themingColor = ThemingColor(light = color, dark = color)

    assertEquals(expected = color, actual = themingColor.mapped(isNight = false))
    assertEquals(expected = color, actual = themingColor.mapped(isNight = true))
    assertEquals(expected = "Light: #FFFFFF, Dark: #FFFFFF", actual = themingColor.toString())

    val alpha = 0.4f
    assertEquals(expected = color.with(alpha = 102), actual = themingColor.with(alpha = alpha).light)
    assertEquals(expected = color.with(alpha = 102), actual = themingColor.with(alpha = alpha).dark)
    assertEquals(expected = "Light: #66FFFFFF, Dark: #66FFFFFF", actual = themingColor.with(alpha = alpha).toString())
  }

  @Test fun lightDark() {
    val light = 0xABCDEF.color
    val dark = 0x123456.color
    val themingColor = ThemingColor(light = light, dark = dark)

    assertEquals(expected = light, actual = themingColor.mapped(isNight = false))
    assertEquals(expected = dark, actual = themingColor.mapped(isNight = true))
    assertEquals(expected = "Light: #ABCDEF, Dark: #123456", actual = themingColor.toString())

    val alpha = 0.8f
    assertEquals(expected = light.with(alpha = 204), actual = themingColor.with(alpha = alpha).light)
    assertEquals(expected = dark.with(alpha = 204), actual = themingColor.with(alpha = alpha).dark)
    assertEquals(expected = "Light: #CCABCDEF, Dark: #CC123456", actual = themingColor.with(alpha = alpha).toString())
  }
}
