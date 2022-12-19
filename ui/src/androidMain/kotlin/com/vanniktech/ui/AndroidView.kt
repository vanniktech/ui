package com.vanniktech.ui

import android.os.Build.VERSION.SDK_INT
import android.webkit.WebView
import android.widget.CheckBox
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

fun SeekBar.themeSeekBar(
  color: Color,
) {
  progressTintList = color.colorStateList()
  thumbTintList = color.colorStateList()
}

fun ProgressBar.themeProgressBar(
  color: Color,
) {
  indeterminateTintList = color.colorStateList()
}

fun RadioButton.themeRadioButton(
  color: Color,
  colorText: Color,
) {
  buttonTintList = color.colorStateList()
  setTextColor(colorText)
}

fun CheckBox.themeCheckBox(
  color: Color,
  colorText: Color,
) {
  buttonTintList = color.colorStateList()
  setTextColor(colorText)
}

fun MaterialAutoCompleteTextView.themeMaterialAutoCompleteTextView(
  color: Color,
  colorText: Color,
  colorTextSecondary: Color,
  colorRipple: Color,
  colorDropDownBackground: Color,
) {
  themeEditText(
    color = color,
    colorText = colorText,
    colorTextSecondary = colorTextSecondary,
  )

  setDropDownBackgroundDrawable(ColorDrawable(colorDropDownBackground))
  simpleItemSelectedRippleColor = colorRipple.colorStateList()
}

fun MaterialToolbar.themeToolbar(
  colorToolbar: Color,
  colorOnToolbar: Color,
  colorOnToolbarSecondary: Color,
) {
  setBackgroundColor(colorToolbar)
  setTitleTextColor(colorOnToolbar.argb)
  setSubtitleTextColor(colorOnToolbarSecondary.argb)
}

fun TabLayout.themeTabLayout(
  colorToolbar: Color,
  colorOnToolbar: Color,
  colorOnToolbarSecondary: Color,
  colorRipple: Color,
) {
  ViewCompat.setBackgroundTintList(this, colorToolbar.colorStateList())
  setSelectedTabIndicatorColor(colorOnToolbar.argb)
  tabIconTint = colorToolbar.colorStateList()
  tabTextColors = colorStateList(
    state = android.R.attr.state_selected,
    on = colorOnToolbar,
    off = colorOnToolbarSecondary,
  )
  tabRippleColor = colorRipple.colorStateList()
}

fun FloatingActionButton.themeFloatingActionButton(
  color: Color,
  colorOn: Color,
  colorRipple: Color,
) {
  ViewCompat.setBackgroundTintList(this, color.colorStateList())
  setRippleColor(colorRipple.colorStateList())
  imageTintList = colorOn.colorStateList()
}

fun ExtendedFloatingActionButton.themeExtendedFloatingActionButton(
  color: Color,
  colorOn: Color,
  colorRipple: Color,
) {
  ViewCompat.setBackgroundTintList(this, color.colorStateList())
  rippleColor = colorRipple.colorStateList()
  setTextColor(colorOn)
  iconTint = colorOn.colorStateList()
}

fun WebView.themeWebView(
  backgroundColor: Color,
  isNight: Boolean,
) {
  setBackgroundColor(backgroundColor)

  if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
    // Remove SDK_INT check once they have removed their annotation.
    // https://issuetracker.google.com/issues/243570060#comment9
    if (SDK_INT >= 29) {
      WebSettingsCompat.setAlgorithmicDarkeningAllowed(settings, isNight)
    }
  } else {
    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
      @Suppress("DEPRECATION")
      WebSettingsCompat.setForceDark(
        settings,
        when {
          isNight -> WebSettingsCompat.FORCE_DARK_ON
          else -> WebSettingsCompat.FORCE_DARK_OFF
        },
      )
    }
  }
}

fun RecyclerView.themeRecyclerView(color: Color) {
  edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
    override fun createEdgeEffect(view: RecyclerView, direction: Int) =
      EdgeEffect(view.context).apply {
        this.color = color.argb
      }
  }
}

fun SwipeRefreshLayout.themeSwipeRefreshLayout(
  color: Color,
  backgroundColor: Color,
) {
  setColorSchemeColors(color.argb)
  setProgressBackgroundColorSchemeColor(backgroundColor.argb)
}

fun TextView.themeTextView(
  color: Color,
  colorText: Color,
  colorTextSecondary: Color,
) {
  highlightColor = color.argb
  setHandlesColor(color)
  setHintTextColor(colorTextSecondary.argb)
  setLinkTextColor(color.argb)
  setTextColor(colorText)
  TextViewCompat.setCompoundDrawableTintList(this, colorText.colorStateList())
}

fun EditText.themeEditText(
  color: Color,
  colorText: Color,
  colorTextSecondary: Color,
) {
  themeTextView(
    color = color,
    colorText = colorText,
    colorTextSecondary = colorTextSecondary,
  )

  setCursorDrawableColor(color)
  ViewCompat.setBackgroundTintList(this, color.colorStateList())
}

fun BottomNavigationView.themeBottomNavigationView(
  colorBackground: Color,
  colorOnBackground: Color,
  colorOnBackgroundSecondary: Color,
  colorRipple: Color,
) {
  val text = colorStateList(
    state = android.R.attr.state_checked,
    on = colorOnBackground,
    off = colorOnBackgroundSecondary,
  )

  itemIconTintList = text
  itemRippleColor = colorRipple.colorStateList()
  itemTextColor = text
  setBackgroundColor(colorBackground)
}

fun TextInputLayout.themeTextInputLayout(
  colorText: Color,
  colorError: Color,
  colorBoxPrimary: Color,
  colorBoxSecondary: Color,
) {
  counterOverflowTextColor = colorText.colorStateList()
  counterTextColor = colorText.colorStateList()
  defaultHintTextColor = colorText.colorStateList()
  hintTextColor = colorText.colorStateList()
  placeholderTextColor = colorText.colorStateList()
  setEndIconTintList(colorText.colorStateList())
  setHelperTextColor(colorText.colorStateList())
  setPrefixTextColor(colorText.colorStateList())
  setStartIconTintList(colorText.colorStateList())
  setSuffixTextColor(colorText.colorStateList())

  setErrorIconTintList(colorError.colorStateList())
  boxStrokeErrorColor = colorError.colorStateList()

  setBoxStrokeColorStateList(
    colorStateList(
      state = android.R.attr.state_focused,
      on = colorBoxPrimary,
      off = colorBoxSecondary,
    ),
  )
}

fun Chip.themeChip(
  colorBackground: Color,
  colorBackgroundSelected: Color,
  colorText: Color,
  colorTextSelected: Color,
  colorRipple: Color,
) {
  val textColor = colorStateList(
    state = android.R.attr.state_selected,
    on = colorTextSelected,
    off = colorText,
  )
  closeIconTint = textColor
  chipIconTint = textColor
  setTextColor(textColor)

  rippleColor = colorRipple.colorStateList()
  chipBackgroundColor = colorStateList(
    state = android.R.attr.state_selected,
    on = colorBackgroundSelected,
    off = colorBackground,
  )
}

fun MaterialButton.themeButton(
  colorBackground: Color,
  colorBackgroundDisabled: Color,
  colorText: Color,
  colorTextDisabled: Color,
  colorRipple: Color,
) {
  ViewCompat.setBackgroundTintList(
    this,
    colorStateList(
      state = android.R.attr.state_enabled,
      on = colorBackground,
      off = colorBackgroundDisabled,
    ),
  )

  val textColor = colorStateList(
    state = android.R.attr.state_enabled,
    on = colorText,
    off = colorTextDisabled,
  )
  setTextColor(textColor)
  iconTint = textColor
  rippleColor = colorRipple.colorStateList()
}

fun MaterialButton.themeOutlineButton(
  color: Color,
  colorDisabled: Color,
  colorRipple: Color,
) {
  val textColor = colorStateList(
    state = android.R.attr.state_enabled,
    on = color,
    off = colorDisabled,
  )
  setTextColor(textColor)
  iconTint = textColor

  strokeColor = textColor

  if (SDK_INT >= 23) {
    foregroundTintList = null
  }

  rippleColor = colorRipple.colorStateList()
}

fun MaterialButton.themeToggleButton(
  color: Color,
  colorUnchecked: Color,
  colorRipple: Color,
) {
  val textColor = colorStateList(
    state = android.R.attr.state_checked,
    on = color,
    off = colorUnchecked,
  )
  setTextColor(textColor)
  iconTint = textColor

  strokeColor = textColor

  if (SDK_INT >= 23) {
    foregroundTintList = null
  }

  setBackgroundColor(android.graphics.Color.TRANSPARENT)

  rippleColor = colorRipple.colorStateList()
}

fun MaterialButton.themeTextButton(
  color: Color,
  colorDisabled: Color,
  colorRipple: Color,
) {
  val textColor = colorStateList(
    state = android.R.attr.state_enabled,
    on = color,
    off = colorDisabled,
  )
  setTextColor(textColor)
  iconTint = textColor

  strokeColor = Color.TRANSPARENT.colorStateList()

  if (SDK_INT >= 23) {
    foregroundTintList = null
  }

  rippleColor = colorRipple.colorStateList()
}
