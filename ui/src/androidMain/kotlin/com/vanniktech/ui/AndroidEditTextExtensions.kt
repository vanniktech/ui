package com.vanniktech.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

private val Context.inputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun EditText.showKeyboardAndFocus() {
  post {
    requestFocus()
    context.inputMethodManager.showSoftInput(this, 0)
  }
}

fun EditText.hideKeyboardAndFocus() {
  post {
    clearFocus()
    context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
  }
}

fun Activity.hideKeyboard() {
  currentFocus?.let { hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
  inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

fun EditText.cursorAtEnd() = setSelection(text.length)

fun EditText.cursorAtEndWithText(text: String?) {
  setText(text)
  cursorAtEnd()
}
