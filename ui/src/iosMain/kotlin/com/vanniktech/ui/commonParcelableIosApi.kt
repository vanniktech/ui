@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.vanniktech.ui

import kotlin.time.Duration

actual interface Parcelable
actual interface Parceler<T>

actual object DurationParceler : Parceler<Duration?>
