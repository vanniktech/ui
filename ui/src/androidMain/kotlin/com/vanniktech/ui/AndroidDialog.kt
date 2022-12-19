package com.vanniktech.ui

import android.content.DialogInterface
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun MaterialAlertDialogBuilder.show(
  background: Color,
  colorRipple: Color,
  colorButton: Color,
): AlertDialog {
  this.background = ColorDrawable(background)

  fun Button.tint() {
    setTextColor(colorButton)

    if (this is MaterialButton) {
      rippleColor = colorRipple.colorStateList()
    }
  }

  val alertDialog = show()

  alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)?.tint()
  alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL)?.tint()
  alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)?.tint()

  return alertDialog
}
