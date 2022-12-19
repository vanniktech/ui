package com.vanniktech.ui

import android.view.Menu

fun Menu.themeMenu(
  color: Color,
  subMenuColor: Color,
) {
  (0 until size())
    .map { getItem(it) }
    .forEach {
      it.subMenu?.themeMenu(subMenuColor, subMenuColor)
      it.title = color.coloredText(it.title)
      it.icon?.tinted(color)
    }
}
