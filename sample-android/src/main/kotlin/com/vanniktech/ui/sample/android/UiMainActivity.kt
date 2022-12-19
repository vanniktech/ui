package com.vanniktech.ui.sample.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.ui.Color
import com.vanniktech.ui.sample.android.databinding.ActivityMainBinding
import com.vanniktech.ui.setTextColor

class UiMainActivity : AppCompatActivity() {
  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.textView.setTextColor(Color.UNTINTED)
    binding.textView.text = Color.UNTINTED.toString()
  }
}
