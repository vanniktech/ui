package com.vanniktech.ui.sample.android

import android.app.Application
import com.vanniktech.ui.theming.night.AndroidNightModeHandler
import timber.log.Timber
import timber.log.Timber.DebugTree

class UiApplication : Application() {
  val androidNightModeHandler = AndroidNightModeHandler(this)

  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }
}
