package com.vanniktech.ui

import android.os.Bundle
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class DurationParcelerTest {
  @Test fun durationParcelable() {
    val bundle = Bundle()

    val allSet = DurationParcelable(
      nullableDuration = 5.seconds,
      duration = 1.seconds,
    )

    val nullable = DurationParcelable(
      nullableDuration = null,
      duration = 1.seconds,
    )

    bundle.putParcelable("allSet", allSet)
    assertEquals(allSet, @Suppress("DEPRECATION") bundle.getParcelable("allSet"))

    bundle.putParcelable("nullable", nullable)
    assertEquals(nullable, @Suppress("DEPRECATION") bundle.getParcelable("nullable"))
  }
}

@Parcelize class DurationParcelable(
  @TypeParceler<Duration?, DurationParceler>() val nullableDuration: Duration?,
  @TypeParceler<Duration, DurationParceler>() val duration: Duration,
) : Parcelable
