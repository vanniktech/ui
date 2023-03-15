package com.vanniktech.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.PorterDuff
import android.os.Build.VERSION
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.transition.AutoTransition
import androidx.transition.Transition
import androidx.transition.Transition.TransitionListener
import androidx.transition.TransitionManager

/** Counterpart of [View.animateToGone]. */
fun View.animateToVisible(
  duration: Long = DEFAULT_ANIMATION_DURATION,
  onEnd: (() -> Unit)? = null,
) {
  if (visibility == VISIBLE) return

  alpha = 0.0f

  animate()
    .setDuration(duration)
    .alpha(1.0f)
    .setListener(object : AnimatorListenerAdapter() {
      override fun onAnimationStart(animation: Animator) {
        visibility = VISIBLE
      }

      override fun onAnimationEnd(animation: Animator) {
        onEnd?.invoke()
      }
    },
    )
}

/** Counterpart of [View.animateToVisible]. */
fun View.animateToGone(
  duration: Long = DEFAULT_ANIMATION_DURATION,
  onEnd: (() -> Unit)? = null,
) {
  if (visibility == GONE) {
    onEnd?.invoke()
    return
  }

  alpha = 1.0f

  animate()
    .setDuration(duration)
    .alpha(0.0f)
    .setListener(object : AnimatorListenerAdapter() {
      override fun onAnimationEnd(animation: Animator) {
        visibility = GONE
        onEnd?.invoke()
      }
    },
    )
}

fun View.animateBackgroundColor(color: Color, speed: Long = DEFAULT_ANIMATION_DURATION) {
  val backgroundColor = backgroundColor()

  if (backgroundColor == null) {
    setBackgroundColor(color)
  } else {
    ObjectAnimator.ofObject(this, "backgroundColor", ArgbEvaluator(), backgroundColor.argb, color.argb).apply {
      duration = speed
      start()
    }
  }
}

fun TextView.animateTextColor(color: Color, speed: Long = DEFAULT_ANIMATION_DURATION) {
  ObjectAnimator.ofObject(this, "textColor", ArgbEvaluator(), currentTextColor, color.argb).apply {
    duration = speed
    start()
  }
}

fun ImageView.animateTint(to: Color, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN, speed: Long = DEFAULT_ANIMATION_DURATION) {
  val from = tag as? Color
  tag = to

  if (from == null) {
    tintIcon(to, mode)
    return
  }

  ValueAnimator.ofInt(from.argb, to.argb).apply {
    setEvaluator(ArgbEvaluator())

    addUpdateListener { animation ->
      setColorFilter(animation.animatedValue as Int, mode)
    }

    duration = speed
    start()
  }
}

const val DEFAULT_ANIMATION_DURATION = 300L

fun TextView.animateText(text: String?) {
  animateToGone {
    setText(text)
    animateToVisible()
  }
}

fun View.flash(from: Color, to: Color) {
  val anim = ObjectAnimator.ofInt(this, "backgroundColor", from.argb, to.argb)
  anim.startDelay = 300
  anim.duration = 1000
  anim.setEvaluator(ArgbEvaluator())
  anim.repeatMode = ValueAnimator.REVERSE
  anim.repeatCount = 1
  anim.start()
}

inline fun ViewGroup.animate(body: () -> Unit) {
  if (VERSION.SDK_INT >= 23) {
    TransitionManager.endTransitions(this)
  }

  TransitionManager.beginDelayedTransition(this)

  body()
}

inline fun ViewGroup.animate(body: () -> Unit, crossinline onEndListener: () -> Unit) {
  if (VERSION.SDK_INT >= 23) {
    TransitionManager.endTransitions(this)
  }

  TransitionManager.beginDelayedTransition(
    this,
    AutoTransition().addListener(object : TransitionListener {
      override fun onTransitionEnd(transition: Transition) = onEndListener.invoke()

      override fun onTransitionResume(transition: Transition) = Unit

      override fun onTransitionPause(transition: Transition) = Unit

      override fun onTransitionCancel(transition: Transition) = Unit

      override fun onTransitionStart(transition: Transition) = Unit
    },
    ),
  )

  body()
}
