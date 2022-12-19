package com.vanniktech.ui.sample.android

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class UiApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
  }
}
