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
        override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) = set(activity)
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = set(activity)
        override fun onActivityPreStarted(activity: Activity) = set(activity)
        override fun onActivityStarted(activity: Activity) = set(activity)
        override fun onActivityPreResumed(activity: Activity) = set(activity)
        override fun onActivityResumed(activity: Activity) = set(activity)

        override fun onActivityPaused(activity: Activity) = Unit
        override fun onActivityStopped(activity: Activity) = Unit
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
        override fun onActivityDestroyed(activity: Activity) = Unit

        private fun set(activity: Activity) {
          weak = WeakReference(activity)
        }
      },
      )
    }
  }

  override fun isNightMode() = when (AppCompatDelegate.getDefaultNightMode()) {
    AppCompatDelegate.MODE_NIGHT_YES -> true
    AppCompatDelegate.MODE_NIGHT_NO -> false
    else -> (weak?.get() ?: context).resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
  }

  override fun updateBehavior(nightModeBehavior: NightModeBehavior) {
    AppCompatDelegate.setDefaultNightMode(nightModeBehavior.value)
  }
}
