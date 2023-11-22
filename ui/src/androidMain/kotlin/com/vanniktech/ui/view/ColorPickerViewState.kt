package com.vanniktech.ui.view

import android.view.View
import com.vanniktech.ui.Color
import com.vanniktech.ui.Parcelable
import com.vanniktech.ui.Parcelize

@Parcelize internal class ColorPickerViewState(
  val superSavedState: Parcelable?,
  val color: Color,
  val supportsAlpha: Boolean,
) : View.BaseSavedState(superSavedState),
  Parcelable
