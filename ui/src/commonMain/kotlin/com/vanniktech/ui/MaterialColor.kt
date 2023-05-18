package com.vanniktech.ui

/** Taken from https://m2.material.io/design/color/the-color-system.html#tools-for-picking-colors */
enum class MaterialColor(
  val color400: Color,
  val color500: Color,
  val color800: Color,
  /** Taken from https://material.io/resources/color based on [color500]. */
  val colorToolbar: Color,
) {
  RED(color400 = 0xFFEF5350.color, color500 = 0xFFF44336.color, color800 = 0xFFC62828.color, colorToolbar = 0xFFBA000D.color),
  PINK(color400 = 0xFFEC407A.color, color500 = 0xFFE91E63.color, color800 = 0xFFAD1457.color, colorToolbar = 0xFFB0003A.color),
  PURPLE(color400 = 0xFFAB47BC.color, color500 = 0xFF9C27B0.color, color800 = 0xFF6A1B9A.color, colorToolbar = 0xFF6A0080.color),
  PURPLE_DEEP(color400 = 0xFF7E57C2.color, color500 = 0xFF673AB7.color, color800 = 0xFF4527A0.color, colorToolbar = 0xFF320B86.color),
  INDIGO(color400 = 0xFF5C6BC0.color, color500 = 0xFF3F51B5.color, color800 = 0xFF283593.color, colorToolbar = 0xFF002984.color),
  BLUE(color400 = 0xFF42A5F5.color, color500 = 0xFF2196F3.color, color800 = 0xFF1565C0.color, colorToolbar = 0xFF0069C0.color),
  BLUE_LIGHT(color400 = 0xFF29B6F6.color, color500 = 0xFF03A9F4.color, color800 = 0xFF0277BD.color, colorToolbar = 0xFF007AC1.color),
  CYAN(color400 = 0xFF26C6DA.color, color500 = 0xFF00BCD4.color, color800 = 0xFF00838F.color, colorToolbar = 0xFF008BA3.color),
  TEAL(color400 = 0xFF26A69A.color, color500 = 0xFF009688.color, color800 = 0xFF00695C.color, colorToolbar = 0xFF00675B.color),
  GREEN(color400 = 0xFF66BB6A.color, color500 = 0xFF4CAF50.color, color800 = 0xFF2E7D32.color, colorToolbar = 0xFF087F23.color),
  GREEN_LIGHT(color400 = 0xFF9CCC65.color, color500 = 0xFF8BC34A.color, color800 = 0xFF558B2F.color, colorToolbar = 0xFF5A9216.color),
  LIME(color400 = 0xFFD4E157.color, color500 = 0xFFCDDC39.color, color800 = 0xFF9E9D24.color, colorToolbar = 0xFF99AA00.color),
  YELLOW(color400 = 0xFFFFEE58.color, color500 = 0xFFFFEB3B.color, color800 = 0xFFF9A825.color, colorToolbar = 0xFFC8B900.color),
  AMBER(color400 = 0xFFFFCA28.color, color500 = 0xFFFFC107.color, color800 = 0xFFFF8F00.color, colorToolbar = 0xFFC79100.color),
  ORANGE(color400 = 0xFFFFA726.color, color500 = 0xFFFF9800.color, color800 = 0xFFEF6C00.color, colorToolbar = 0xFFC66900.color),
  ORANGE_DEEP(color400 = 0xFFFF7043.color, color500 = 0xFFFF5722.color, color800 = 0xFFD84315.color, colorToolbar = 0xFFC41C00.color),
  BROWN(color400 = 0xFF8D6E63.color, color500 = 0xFF795548.color, color800 = 0xFF4E342E.color, colorToolbar = 0xFF4B2C20.color),
  GRAY(color400 = 0xFFBDBDBD.color, color500 = 0xFF9E9E9E.color, color800 = 0xFF424242.color, colorToolbar = 0xFF707070.color),
  BLUE_GRAY(color400 = 0xFF78909C.color, color500 = 0xFF607D8B.color, color800 = 0xFF37474F.color, colorToolbar = 0xFF34515E.color),
}
