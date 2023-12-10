@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.vanniktech.ui

import android.os.Parcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.TypeParceler
import kotlin.time.Duration

actual typealias Parcelable = android.os.Parcelable
actual typealias Parcelize = kotlinx.parcelize.Parcelize
actual typealias IgnoredOnParcel = kotlinx.parcelize.IgnoredOnParcel
actual typealias TypeParceler<T, P> = TypeParceler<T, P>
actual typealias Parceler<T> = Parceler<T>

actual object DurationParceler : Parceler<Duration?> {
  override fun create(parcel: Parcel): Duration? = when (parcel.readInt() == 1) {
    true -> parcel.readString()?.let(Duration::parseIsoStringOrNull)
    else -> null
  }

  override fun Duration?.write(parcel: Parcel, flags: Int) {
    parcel.writeInt(if (this != null) 1 else 0)

    if (this != null) {
      parcel.writeString(this.toIsoString())
    }
  }
}
