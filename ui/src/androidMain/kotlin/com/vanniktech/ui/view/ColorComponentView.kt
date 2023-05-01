package com.vanniktech.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.vanniktech.ui.COLOR_COMPONENT_RANGE
import com.vanniktech.ui.Color
import com.vanniktech.ui.ColorDrawable
import com.vanniktech.ui.R
import com.vanniktech.ui.colorStateList
import com.vanniktech.ui.cursorAtEndWithText
import com.vanniktech.ui.databinding.UiViewColorComponentBinding
import com.vanniktech.ui.hideKeyboardAndFocus
import com.vanniktech.ui.themeEditText
import com.vanniktech.ui.themeSeekBar
import com.vanniktech.ui.themeTextView
import com.vanniktech.ui.theming.UiTheming

internal interface ColorComponentDelegate {
  fun onComponentChanged(componentChange: ColorComponentChange)
  fun hideKeyboardAndFocus()
}

internal data class ColorComponentChange(
  val origin: ColorComponentView,
  val value: Int,
)

internal class ColorComponentView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
  private val binding = UiViewColorComponentBinding.inflate(LayoutInflater.from(context), this)

  private val height = resources.getDimensionPixelSize(R.dimen.ui_color_component_seekbar_height)
  private val radius = height / 2f

  internal lateinit var delegate: ColorComponentDelegate

  private val textWatcher = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    override fun afterTextChanged(s: Editable?) {
      val value = s?.toString()?.toIntOrNull() ?: 0
      binding.seekBar.progress = value
      delegate.onComponentChanged(ColorComponentChange(origin = this@ColorComponentView, value = value))
    }
  }

  private val seekBarChangeListener = object : OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
      if (fromUser) {
        binding.editText.cursorAtEndWithText(progress.toString())
        delegate.hideKeyboardAndFocus()
      }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
    override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
  }

  init {
    orientation = VERTICAL
  }

  fun hideKeyboardAndFocus() = binding.editText.hideKeyboardAndFocus()

  fun setUp(
    header: String,
    initialValue: Int,
    theming: UiTheming,
    delegate: ColorComponentDelegate,
  ) {
    this.delegate = delegate

    binding.header.text = header
    binding.header.themeTextView(
      color = theming.colorSecondary(),
      colorText = theming.colorText(),
      colorTextSecondary = theming.colorTextSecondary(),
    )
    binding.seekBar.themeSeekBar(Color.WHITE)

    binding.seekBar.setPadding(height / 2, 0, height / 2, 0)
    binding.seekBar.progressDrawable = ColorDrawable(Color.TRANSPARENT)
    binding.seekBar.max = COLOR_COMPONENT_RANGE.last
    binding.seekBar.progress = initialValue

    binding.editText.themeEditText(
      color = theming.colorSecondary(),
      colorText = theming.colorText(),
      colorTextSecondary = theming.colorTextSecondary(),
    )

    binding.editText.cursorAtEndWithText(initialValue.toString())
    binding.editText.filters = arrayOf(ColorComponentInputFilter)
  }

  fun changeBackground(
    from: Color,
    to: Color,
    thumbColor: ColorStateList,
    value: Int,
  ) {
    binding.seekBar.thumbTintList = thumbColor
    binding.seekBar.thumb = GradientDrawable().apply {
      shape = GradientDrawable.OVAL
      color = Color.TRANSPARENT.colorStateList()
      cornerRadius = radius
      setSize(height, height)

      setStroke(resources.getDimensionPixelSize(R.dimen.ui_color_component_seekbar_thumb_stroke_width), thumbColor)
    }

    binding.seekBar.background = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(from.argb, to.argb)).apply {
      cornerRadii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
    }

    updateValue(value)
  }

  private fun updateValue(value: Int) {
    // First clear all listeners.
    binding.editText.removeTextChangedListener(textWatcher)
    binding.seekBar.setOnSeekBarChangeListener(null)

    // Then set the stuff.
    binding.editText.cursorAtEndWithText(value.toString())
    binding.seekBar.progress = value

    // Then add the listeners back.
    binding.editText.addTextChangedListener(textWatcher)
    binding.seekBar.setOnSeekBarChangeListener(seekBarChangeListener)
  }
}
