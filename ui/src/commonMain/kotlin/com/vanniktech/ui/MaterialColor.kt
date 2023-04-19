package com.vanniktech.ui

/** Taken from https://m2.material.io/design/color/the-color-system.html#tools-for-picking-colors */
enum class MaterialColor(
  val color400: Color,
  val color500: Color,
  val color800: Color,
) {
  RED(color400 = 0xFFEF5350.color, color500 = 0xFFF44336.color, color800 = 0xFFC62828.color),
  PINK(color400 = 0xFFEC407A.color, color500 = 0xFFE91E63.color, color800 = 0xFFAD1457.color),
  PURPLE(color400 = 0xFFAB47BC.color, color500 = 0xFF9C27B0.color, color800 = 0xFF6A1B9A.color),
  PURPLE_DEEP(color400 = 0xFF7E57C2.color, color500 = 0xFF673AB7.color, color800 = 0xFF4527A0.color),
  INDIGO(color400 = 0xFF5C6BC0.color, color500 = 0xFF3F51B5.color, color800 = 0xFF283593.color),
  BLUE(color400 = 0xFF42A5F5.color, color500 = 0xFF2196F3.color, color800 = 0xFF1565C0.color),
  BLUE_LIGHT(color400 = 0xFF29B6F6.color, color500 = 0xFF03A9F4.color, color800 = 0xFF0277BD.color),
  CYAN(color400 = 0xFF26C6DA.color, color500 = 0xFF00BCD4.color, color800 = 0xFF00838F.color),
  TEAL(color400 = 0xFF26A69A.color, color500 = 0xFF009688.color, color800 = 0xFF00695C.color),
  GREEN(color400 = 0xFF66BB6A.color, color500 = 0xFF4CAF50.color, color800 = 0xFF2E7D32.color),
  GREEN_LIGHT(color400 = 0xFF9CCC65.color, color500 = 0xFF8BC34A.color, color800 = 0xFF558B2F.color),
  LIME(color400 = 0xFFD4E157.color, color500 = 0xFFCDDC39.color, color800 = 0xFF9E9D24.color),
  YELLOW(color400 = 0xFFFFEE58.color, color500 = 0xFFFFEB3B.color, color800 = 0xFFF9A825.color),
  AMBER(color400 = 0xFFFFCA28.color, color500 = 0xFFFFC107.color, color800 = 0xFFFF8F00.color),
  ORANGE(color400 = 0xFFFFA726.color, color500 = 0xFFFF9800.color, color800 = 0xFFEF6C00.color),
  ORANGE_DEEP(color400 = 0xFFFF7043.color, color500 = 0xFFFF5722.color, color800 = 0xFFD84315.color),
  BROWN(color400 = 0xFF8D6E63.color, color500 = 0xFF795548.color, color800 = 0xFF4E342E.color),
  GRAY(color400 = 0xFFBDBDBD.color, color500 = 0xFF9E9E9E.color, color800 = 0xFF424242.color),
  BLUE_GRAY(color400 = 0xFF78909C.color, color500 = 0xFF607D8B.color, color800 = 0xFF37474F.color),
  ;
}
