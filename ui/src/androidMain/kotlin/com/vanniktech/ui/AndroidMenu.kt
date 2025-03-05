package com.vanniktech.ui

import android.view.Menu
import androidx.core.view.get
import androidx.core.view.size

fun Menu.themeMenu(
  color: Color,
  subMenuColor: Color,
) {
  (0 until size)
    .map { this[it] }
    .forEach {
      it.subMenu?.themeMenu(subMenuColor, subMenuColor)
      it.title = color.coloredText(it.title)
      it.icon?.tinted(color)
    }
}
