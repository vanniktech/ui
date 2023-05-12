package com.vanniktech.ui.sample.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.ui.Color
import com.vanniktech.ui.MaterialColor
import com.vanniktech.ui.sample.android.databinding.ActivityMainBinding
import com.vanniktech.ui.setBackgroundColor
import com.vanniktech.ui.setTextColor
import com.vanniktech.ui.themeWindow
import com.vanniktech.ui.theming.night.AndroidNightModeBehaviorHandler
import com.vanniktech.ui.theming.night.ContextNightModeProvider
import com.vanniktech.ui.theming.night.NightModeBehavior
import com.vanniktech.ui.view.ColorPickerStringsHardcoded
import kotlin.random.Random

class UiMainActivity : AppCompatActivity() {
  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.textView.setTextColor(Color.UNTINTED)
    binding.textView.text = Color.UNTINTED.toString()

    val isNightMode = ContextNightModeProvider(this).isNightMode()
    val theming = UiSampleTheming(isNight = isNightMode, tint = MaterialColor.INDIGO.color500)

    val backgroundColor = theming.colorBackgroundPrimary()
    binding.root.setBackgroundColor(backgroundColor)

    binding.nightMode.setTextColor(theming.colorTextPrimary())

    window.themeWindow(
      statusBarColor = backgroundColor,
      navigationBarColor = backgroundColor,
      lightNavigationBars = !isNightMode,
      lightStatusBars = !isNightMode,
    )

    binding.nightMode.text = "Is night mode: $isNightMode"

    binding.nightModeYes.setOnClickListener { AndroidNightModeBehaviorHandler.updateBehavior(NightModeBehavior.YES) }
    binding.nightModeNo.setOnClickListener { AndroidNightModeBehaviorHandler.updateBehavior(NightModeBehavior.NO) }
    binding.nightModeFollowSystem.setOnClickListener { AndroidNightModeBehaviorHandler.updateBehavior(NightModeBehavior.FOLLOW_SYSTEM) }

    binding.colorPickerView.setUp(
      strings = ColorPickerStringsHardcoded(
        alpha = "Alpha".takeIf { Random.nextBoolean() },
        red = "Red",
        green = "Green",
        blue = "Blue",
        hex = "Hex",
      ),
      theming = theming,
      selectedColor = theming.colorSecondary(),
    )
  }
}
