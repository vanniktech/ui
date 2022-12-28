package com.vanniktech.ui.theming.night

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.vanniktech.ui.asActivityOrNull
import java.lang.ref.WeakReference

class AndroidNightModeHandler(
  private val context: Context,
) : NightModeHandler {
  private var weak: WeakReference<Context>? = null

  init {
    val activity = context.asActivityOrNull()

    if (activity != null) {
      weak = WeakReference(activity)
    } else if (context is Application) {
      context.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
        override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
          weak = WeakReference(activity)
        }
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
        override fun onActivityStarted(activity: Activity) = Unit
        override fun onActivityResumed(activity: Activity) = Unit
        override fun onActivityPaused(activity: Activity) = Unit
        override fun onActivityStopped(activity: Activity) = Unit
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
        override fun onActivityDestroyed(activity: Activity) = Unit
      },
      )
    }
  }

  override fun isNightMode(): Boolean {
    val context = weak?.get() ?: context
    val isNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    val isNotOptedOut = AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO
    return isNightMode && isNotOptedOut
  }

  override fun updateBehavior(nightModeBehavior: NightModeBehavior) {
    AppCompatDelegate.setDefaultNightMode(nightModeBehavior.value)
  }
}
