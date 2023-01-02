package com.vanniktech.ui.sample.android

import android.app.Application
import com.vanniktech.ui.theming.night.AndroidNightModeBehaviorHandler
import com.vanniktech.ui.theming.night.NightModeBehavior.FOLLOW_SYSTEM
import timber.log.Timber
import timber.log.Timber.DebugTree

class UiApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())

    AndroidNightModeBehaviorHandler.updateBehavior(FOLLOW_SYSTEM)
  }
}
