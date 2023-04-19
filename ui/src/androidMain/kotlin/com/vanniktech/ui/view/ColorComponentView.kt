package com.vanniktech.ui.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.widget.addTextChangedListener
import com.vanniktech.ui.COLOR_COMPONENT_RANGE
import com.vanniktech.ui.Color
import com.vanniktech.ui.ColorDrawable
import com.vanniktech.ui.R
import com.vanniktech.ui.R.dimen
import com.vanniktech.ui.clearAppend
import com.vanniktech.ui.colorStateList
import com.vanniktech.ui.databinding.UiViewColorComponentBinding
import com.vanniktech.ui.hideKeyboardAndFocus
import com.vanniktech.ui.themeEditText
import com.vanniktech.ui.themeSeekBar
import com.vanniktech.ui.themeTextView
import com.vanniktech.ui.theming.UiTheming

internal class ColorComponentView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
  private val binding = UiViewColorComponentBinding.inflate(LayoutInflater.from(context), this)

  private val height = resources.getDimensionPixelSize(R.dimen.ui_color_component_seekbar_height)
  private val radius = height / 2f

  init {
    orientation = VERTICAL
  }

  fun hideKeyboardAndFocus() = binding.editText.hideKeyboardAndFocus()

  fun setUp(
    header: String,
    initialValue: Int,
    theming: UiTheming,
    onValueChanged: (Int) -> Unit,
  ) {
    binding.header.text = header
    binding.header.themeTextView(
      color = theming.colorSecondary(),
      colorText = theming.colorText(),
      colorTextSecondary = theming.colorTextSecondary(),
    )
    binding.seekBar.themeSeekBar(Color.WHITE)

    binding.seekBar.setPadding(height / 2, 0, height / 2, 0)
    binding.seekBar.progressDrawable = ColorDrawable(Color.TRANSPARENT)
    binding.seekBar.thumb = GradientDrawable().apply {
      shape = GradientDrawable.OVAL
      color = Color.TRANSPARENT.colorStateList()
      cornerRadius = radius
      setSize(height, height)
      setStroke(resources.getDimensionPixelSize(dimen.ui_color_component_seekbar_thumb_stroke_width), Color.WHITE.colorStateList())
    }

    binding.seekBar.max = COLOR_COMPONENT_RANGE.last
    binding.seekBar.progress = initialValue
    binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
      override fun onProgressChanged(
        seekBar: SeekBar?,
        progress: Int,
        fromUser: Boolean,
      ) {
        if (fromUser) {
          binding.editText.clearAppend(progress.toString())
        }
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
      override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
    },
    )

    binding.editText.themeEditText(
      color = theming.colorSecondary(),
      colorText = theming.colorText(),
      colorTextSecondary = theming.colorTextSecondary(),
    )

    binding.editText.clearAppend(initialValue.toString())
    binding.editText.filters = arrayOf(ColorComponentInputFilter)
    binding.editText.addTextChangedListener {
      val string = it?.toString()
      val value = when {
        string.isNullOrBlank() -> 0
        else -> string.toInt()
      }
      onValueChanged(value)
      binding.seekBar.progress = value
    }
  }

  fun changeBackground(from: Color, to: Color) {
    binding.seekBar.background = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(from.argb, to.argb)).apply {
      cornerRadii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
    }
  }
}
