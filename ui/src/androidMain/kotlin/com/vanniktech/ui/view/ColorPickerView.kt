package com.vanniktech.ui.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.vanniktech.ui.COLOR_COMPONENT_RANGE
import com.vanniktech.ui.Color
import com.vanniktech.ui.FLOAT_VALUE
import com.vanniktech.ui.HEX_PREFIX
import com.vanniktech.ui.Parcelable
import com.vanniktech.ui.R
import com.vanniktech.ui.clearAppend
import com.vanniktech.ui.colorStateList
import com.vanniktech.ui.databinding.UiViewColorPickerBinding
import com.vanniktech.ui.hideKeyboardAndFocus
import com.vanniktech.ui.themeEditText
import com.vanniktech.ui.themeTextView
import com.vanniktech.ui.theming.UiTheming

sealed interface ColorPickerStrings

data class ColorPickerStringsHardcoded(
  val red: String,
  val green: String,
  val blue: String,
  val hex: String,
) : ColorPickerStrings

data class ColorPickerStringsAndroid(
  @StringRes val red: Int,
  @StringRes val green: Int,
  @StringRes val blue: Int,
  @StringRes val hex: Int,
) : ColorPickerStrings

class ColorPickerView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
  private val binding = UiViewColorPickerBinding.inflate(LayoutInflater.from(context), this)

  private var color: Color = Color.UNTINTED

  private val hexEditTextWatcher = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      // To support copy and paste. This is part II.
      if (s.isNotEmpty() && s.length == count) {
        val color = Color.fromHexOrNull(s.substring(before, count))

        if (color != null) {
          val hexString = color.unified.hexString()
          binding.hexEditText.setText(hexString)
          binding.hexEditText.setSelection(hexString.length)
        }
      }
    }

    override fun afterTextChanged(s: Editable?) {
      val string = s?.toString()?.trim()
      val color = when (string?.length) {
        // We only want to update the UI when we have 3 or 6 digits.
        3, 6 -> Color.fromHexOrNull(HEX_PREFIX + string)
        else -> null
      }

      if (color != null) {
        updateColor(color, updateEditText = false)
      }
    }
  }

  init {
    orientation = VERTICAL
  }

  fun color() = color

  override fun onSaveInstanceState(): Parcelable {
    val superState = super.onSaveInstanceState()
    return ColorPickerViewState(superState, color)
  }

  override fun onRestoreInstanceState(state: Parcelable?) {
    val myState = state as? ColorPickerViewState
    super.onRestoreInstanceState(myState?.superSavedState ?: state)

    color = myState?.color ?: Color.UNTINTED
    updateColor(color)
  }

  // We don't support alpha.
  internal val Color.unified get() = copy(alpha = FLOAT_VALUE / FLOAT_VALUE)

  fun setUp(
    theming: UiTheming,
    strings: ColorPickerStrings,
    selectedColor: Color,
  ) {
    updateColor(selectedColor.unified)

    binding.red.setUp(
      header = when (strings) {
        is ColorPickerStringsHardcoded -> strings.red
        is ColorPickerStringsAndroid -> context.getString(strings.red)
      },
      initialValue = color.red(),
      theming = theming,
      onValueChanged = {
        hideKeyboardAndFocus()
        updateColor(color.copy(red = it))
      },
    )

    binding.green.setUp(
      header = when (strings) {
        is ColorPickerStringsHardcoded -> strings.green
        is ColorPickerStringsAndroid -> context.getString(strings.green)
      },
      initialValue = color.green(),
      theming = theming,
      onValueChanged = {
        hideKeyboardAndFocus()
        updateColor(color.copy(green = it))
      },
    )

    binding.blue.setUp(
      header = when (strings) {
        is ColorPickerStringsHardcoded -> strings.blue
        is ColorPickerStringsAndroid -> context.getString(strings.blue)
      },
      initialValue = color.blue(),
      theming = theming,
      onValueChanged = {
        hideKeyboardAndFocus()
        updateColor(color.copy(blue = it))
      },
    )

    binding.hexHeader.text = when (strings) {
      is ColorPickerStringsHardcoded -> strings.hex
      is ColorPickerStringsAndroid -> context.getString(strings.hex)
    }
    binding.hexHeader.themeTextView(
      color = theming.colorSecondary(),
      colorText = theming.colorText(),
      colorTextSecondary = theming.colorTextSecondary(),
    )
    binding.hexEditText.filters = arrayOf(ColorHexInputFilter)
    binding.hexEditText.themeEditText(
      color = theming.colorSecondary(),
      colorText = theming.colorText(),
      colorTextSecondary = theming.colorTextSecondary(),
    )
  }

  private fun hideKeyboardAndFocus() {
    binding.hexEditText.hideKeyboardAndFocus()
    binding.red.hideKeyboardAndFocus()
    binding.green.hideKeyboardAndFocus()
    binding.blue.hideKeyboardAndFocus()
  }

  internal fun updateColor(
    updated: Color,
    updateEditText: Boolean = true,
  ) {
    color = updated

    val highlightColor = when {
      updated.shouldUseBlackFont() -> Color.BLACK
      else -> Color.WHITE
    }.colorStateList()

    binding.preview.background = GradientDrawable().apply {
      color = updated.colorStateList()
      shape = GradientDrawable.RECTANGLE
      val size = context.resources.getDimensionPixelSize(R.dimen.ui_color_picker_preview_height)
      cornerRadius = size / 8f
      setStroke(resources.getDimensionPixelSize(R.dimen.ui_color_picker_preview_stroke_width), highlightColor)
    }

    binding.red.changeBackground(
      updated.copy(red = COLOR_COMPONENT_RANGE.first),
      updated.copy(red = COLOR_COMPONENT_RANGE.last),
      highlightColor,
    )
    binding.green.changeBackground(
      updated.copy(green = COLOR_COMPONENT_RANGE.first),
      updated.copy(green = COLOR_COMPONENT_RANGE.last),
      highlightColor,
    )
    binding.blue.changeBackground(
      updated.copy(blue = COLOR_COMPONENT_RANGE.first),
      updated.copy(blue = COLOR_COMPONENT_RANGE.last),
      highlightColor,
    )

    if (updateEditText) {
      binding.hexEditText.removeTextChangedListener(hexEditTextWatcher)
      binding.hexEditText.clearAppend(updated.toString().removePrefix(HEX_PREFIX))
      binding.hexEditText.addTextChangedListener(hexEditTextWatcher)
    }
  }
}
