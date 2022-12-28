package com.vanniktech.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.asActivityOrNull(): Activity? {
  var result: Context? = this

  while (result is ContextWrapper) {
    if (result is Activity) {
      return result
    }

    result = result.baseContext
  }

  return null
}
