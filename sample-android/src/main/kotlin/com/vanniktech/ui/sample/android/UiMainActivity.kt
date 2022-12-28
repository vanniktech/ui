package com.vanniktech.ui.sample.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.ui.Color
import com.vanniktech.ui.sample.android.databinding.ActivityMainBinding
import com.vanniktech.ui.setBackgroundColor
import com.vanniktech.ui.setTextColor
import com.vanniktech.ui.themeWindow
import com.vanniktech.ui.theming.night.NightModeBehavior

class UiMainActivity : AppCompatActivity() {
  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.textView.setTextColor(Color.UNTINTED)
    binding.textView.text = Color.UNTINTED.toString()

    val androidNightModeHandler = (application as UiApplication).androidNightModeHandler
    val isNightMode = androidNightModeHandler.isNightMode()
    val backgroundColor = when (isNightMode) {
      true -> Color.BLACK
      else -> Color.WHITE
    }
    binding.root.setBackgroundColor(backgroundColor)

    binding.nightMode.setTextColor(
      when (isNightMode) {
        true -> Color.WHITE
        else -> Color.BLACK
      },
    )

    window.themeWindow(
      statusBarColor = backgroundColor,
      navigationBarColor = backgroundColor,
      lightNavigationBars = !isNightMode,
      lightStatusBars = !isNightMode,
    )

    binding.nightMode.text = "Is night mode: $isNightMode"

    binding.nightModeYes.setOnClickListener { androidNightModeHandler.updateBehavior(NightModeBehavior.YES) }
    binding.nightModeNo.setOnClickListener { androidNightModeHandler.updateBehavior(NightModeBehavior.NO) }
    binding.nightModeFollowSystem.setOnClickListener { androidNightModeHandler.updateBehavior(NightModeBehavior.FOLLOW_SYSTEM) }
  }
}
