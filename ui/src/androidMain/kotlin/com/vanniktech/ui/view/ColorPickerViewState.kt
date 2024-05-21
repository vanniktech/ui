package com.vanniktech.ui.view

import android.view.View
import com.vanniktech.ui.Color
import com.vanniktech.ui.UiParcelable
import com.vanniktech.ui.UiParcelize

@UiParcelize internal class ColorPickerViewState(
  val superSavedState: UiParcelable?,
  val color: Color,
  val supportsAlpha: Boolean,
) : View.BaseSavedState(superSavedState),
  UiParcelable
