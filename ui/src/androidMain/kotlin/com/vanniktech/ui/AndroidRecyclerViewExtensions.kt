package com.vanniktech.ui

import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.google.android.flexbox.FlexboxLayoutManager

fun RecyclerView.scrollToTop() = smoothScrollTo(0)
fun RecyclerView.scrollToBottom() = smoothScrollTo(maxOf(0, itemCount() - 1))

private fun RecyclerView.firstVisibleItemPosition() = when (val layoutManager = layoutManager) {
  is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
  is FlexboxLayoutManager -> layoutManager.findFirstVisibleItemPosition()
  else -> error("Unsupported Layout Manager $layoutManager")
}

private fun RecyclerView.itemCount() = adapter?.itemCount ?: 0

fun RecyclerView.scrollBefore() = smoothScrollTo(maxOf(0, firstVisibleItemPosition() - 1))
fun RecyclerView.scrollNext() = smoothScrollTo(minOf(itemCount(), firstVisibleItemPosition() + 1))

fun RecyclerView.smoothScrollTo(position: Int, scrollDuration: Int = 300, onTargetFound: (View) -> Unit = { }) {
  val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
    override fun getVerticalSnapPreference() = SNAP_TO_START

    override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
      super.onTargetFound(targetView, state, action)
      onTargetFound(targetView)
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float = scrollDuration.toFloat() / computeVerticalScrollRange()
  }
  smoothScroller.targetPosition = position
  layoutManager?.startSmoothScroll(smoothScroller)
}

/** This is useful when a [RecyclerView] with the same scrolling direction is inside another [RecyclerView]. */
fun RecyclerView.allowNestedVerticalScrolling() = addOnItemTouchListener(
  object : RecyclerView.OnItemTouchListener {
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
      when (e.action) {
        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
      }
      return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) = Unit

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) = Unit
  },
)
