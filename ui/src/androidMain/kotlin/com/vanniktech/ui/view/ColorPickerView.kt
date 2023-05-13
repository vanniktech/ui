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
import com.vanniktech.ui.colorStateList
import com.vanniktech.ui.cursorAtEndWithText
import com.vanniktech.ui.databinding.UiViewColorPickerBinding
import com.vanniktech.ui.hideKeyboardAndFocus
import com.vanniktech.ui.themeEditText
import com.vanniktech.ui.themeTextView
import com.vanniktech.ui.theming.UiTheming
import com.vanniktech.ui.visibleGone

sealed interface ColorPickerStrings

data class ColorPickerStringsHardcoded(
  /** Null means alpha won't be configurable. */
  val alpha: String?,
  val red: String,
  val green: String,
  val blue: String,
  val hex: String,
) : ColorPickerStrings

data class ColorPickerStringsAndroid(
  /** 0 is optional and alpha won't be configurable. */
  @StringRes val alpha: Int,
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
  private var supportsAlpha = false

  private val hexEditTextWatcher = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      // To support copy and paste. This is part II.
      if (s.isNotEmpty() && s.length == count && before <= count) {
        val color = Color.fromHexOrNull(s.substring(before, count))

        if (color != null) {
          updateColor(updated = color.unified, updateHexEditText = false)
        }
      }
    }

    override fun afterTextChanged(s: Editable?) {
      val string = s?.toString()?.trim().orEmpty()
      val color = inferColor(string, supportsAlpha)

      if (color != null) {
        updateColor(color, updateHexEditText = false)
      }
    }
  }

  init {
    orientation = VERTICAL
  }

  fun color() = color

  override fun onSaveInstanceState(): Parcelable {
    val superState = super.onSaveInstanceState()
    return ColorPickerViewState(superState, color, supportsAlpha)
  }

  override fun onRestoreInstanceState(state: Parcelable?) {
    val myState = state as? ColorPickerViewState
    super.onRestoreInstanceState(myState?.superSavedState ?: state)

    color = myState?.color ?: Color.UNTINTED
    supportsAlpha = myState?.supportsAlpha ?: false
    updateColor(updated = color)
  }

  internal val Color.unified get() = when (supportsAlpha) {
    true -> this
    else -> copy(alpha = FLOAT_VALUE / FLOAT_VALUE)
  }

  fun setUp(
    theming: UiTheming,
    strings: ColorPickerStrings,
    selectedColor: Color,
  ) {
    val alphaHeader = when (strings) {
      is ColorPickerStringsHardcoded -> strings.alpha
      is ColorPickerStringsAndroid -> if (strings.alpha != 0) context.getString(strings.alpha) else null
    }

    supportsAlpha = alphaHeader != null
    updateColor(updated = selectedColor.unified)

    binding.alpha.visibleGone(supportsAlpha)

    if (alphaHeader != null) {
      binding.alpha.setUp(
        header = alphaHeader,
        initialValue = color.alpha(),
        theming = theming,
        delegate = ColorPickerColorComponentDelegate(this),
      )
    }

    binding.red.setUp(
      header = when (strings) {
        is ColorPickerStringsHardcoded -> strings.red
        is ColorPickerStringsAndroid -> context.getString(strings.red)
      },
      initialValue = color.red(),
      theming = theming,
      delegate = ColorPickerColorComponentDelegate(this),
    )

    binding.green.setUp(
      header = when (strings) {
        is ColorPickerStringsHardcoded -> strings.green
        is ColorPickerStringsAndroid -> context.getString(strings.green)
      },
      initialValue = color.green(),
      theming = theming,
      delegate = ColorPickerColorComponentDelegate(this),
    )

    binding.blue.setUp(
      header = when (strings) {
        is ColorPickerStringsHardcoded -> strings.blue
        is ColorPickerStringsAndroid -> context.getString(strings.blue)
      },
      initialValue = color.blue(),
      theming = theming,
      delegate = ColorPickerColorComponentDelegate(this),
    )

    binding.hexHeader.text = when (strings) {
      is ColorPickerStringsHardcoded -> strings.hex
      is ColorPickerStringsAndroid -> context.getString(strings.hex)
    }
    binding.hexHeader.themeTextView(
      color = theming.colorSecondary(),
      colorText = theming.colorTextPrimary(),
      colorTextSecondary = theming.colorTextSecondary(),
    )
    binding.hexEditText.filters = arrayOf(ColorHexInputFilter(supportsAlpha))
    binding.hexEditText.themeEditText(
      color = theming.colorSecondary(),
      colorText = theming.colorTextPrimary(),
      colorTextSecondary = theming.colorTextSecondary(),
    )
  }

  internal fun updateColor(
    updated: Color,
    updateHexEditText: Boolean = true,
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
      from = updated.copy(red = COLOR_COMPONENT_RANGE.first),
      to = updated.copy(red = COLOR_COMPONENT_RANGE.last),
      thumbColor = highlightColor,
      value = updated.red(),
    )

    binding.green.changeBackground(
      from = updated.copy(green = COLOR_COMPONENT_RANGE.first),
      to = updated.copy(green = COLOR_COMPONENT_RANGE.last),
      thumbColor = highlightColor,
      value = updated.green(),
    )

    binding.blue.changeBackground(
      from = updated.copy(blue = COLOR_COMPONENT_RANGE.first),
      to = updated.copy(blue = COLOR_COMPONENT_RANGE.last),
      thumbColor = highlightColor,
      value = updated.blue(),
    )

    if (supportsAlpha) {
      binding.alpha.changeBackground(
        from = updated.copy(alpha = COLOR_COMPONENT_RANGE.first),
        to = updated.copy(alpha = COLOR_COMPONENT_RANGE.last),
        thumbColor = highlightColor,
        value = updated.alpha(),
      )
    }

    if (updateHexEditText) {
      binding.hexEditText.removeTextChangedListener(hexEditTextWatcher)
      binding.hexEditText.cursorAtEndWithText(text = updated.toString().removePrefix(HEX_PREFIX))
      binding.hexEditText.addTextChangedListener(hexEditTextWatcher)
    }
  }

  internal class ColorPickerColorComponentDelegate(
    private val colorPickerView: ColorPickerView,
  ) : ColorComponentDelegate {
    override fun onComponentChanged(componentChange: ColorComponentChange) {
      val color = when (val origin = componentChange.origin) {
        colorPickerView.binding.alpha -> colorPickerView.color.copy(alpha = componentChange.value)
        colorPickerView.binding.red -> colorPickerView.color.copy(red = componentChange.value)
        colorPickerView.binding.green -> colorPickerView.color.copy(green = componentChange.value)
        colorPickerView.binding.blue -> colorPickerView.color.copy(blue = componentChange.value)
        else -> error("Should never happen $origin")
      }
      colorPickerView.updateColor(updated = color)
    }

    override fun hideKeyboardAndFocus() {
      colorPickerView.binding.hexEditText.hideKeyboardAndFocus()
      colorPickerView.binding.red.hideKeyboardAndFocus()
      colorPickerView.binding.green.hideKeyboardAndFocus()
      colorPickerView.binding.blue.hideKeyboardAndFocus()
    }
  }
}
