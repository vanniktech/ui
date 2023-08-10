package com.vanniktech.ui

import kotlin.time.Duration

actual interface Parcelable
actual interface Parceler<T>

actual object DurationParceler : Parceler<Duration?>
