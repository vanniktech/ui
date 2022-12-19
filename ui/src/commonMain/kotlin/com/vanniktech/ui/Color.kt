package com.vanniktech.ui

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline

val Int.color get() = Color(this)

/** https://youtrack.jetbrains.com/issue/KT-4749/ */
val Long.color get() = Color(toInt())

object ColorSerializer : KSerializer<Color> {
  override val descriptor: SerialDescriptor
    get() = PrimitiveSerialDescriptor("Color", STRING)

  override fun deserialize(decoder: Decoder) =
    decoder.decodeInt().color

  override fun serialize(encoder: Encoder, value: Color) {
    encoder.encodeInt(value.argb)
  }
}

@Serializable(ColorSerializer::class)
@Parcelize @JvmInline value class Color(val argb: Int) : Parcelable {
  fun with(alpha: Float) = with(alpha = (alpha.coerceIn(0f, 1f) * 255.0).toInt())
  fun with(alpha: Int) = Color(argb and 0x00FFFFFF or (alpha.coerceIn(0, 255) shl 24))

  override fun toString(): String {
    if (argb == 0) return "#00000000"

    var number = argb
    var buffer = ""

    while (number != 0) {
      buffer += HEX_DIGITS[number and 0xF]
      number = number ushr 4
    }

    return "#${buffer.reversed()}"
  }

  fun htmlRgbaString() = "rgba($red, $green, $blue, $alpha)"

  fun shouldUseBlackFont(): Boolean {
    val lum = 0.299 * red + (0.587 * green + 0.114 * blue)
    return lum > 186
  }

  private val alpha get() = argb shr 24 and 0xFF
  private val red get() = argb shr 16 and 0xFF
  private val green get() = argb shr 8 and 0xFF
  private val blue get() = argb and 0xFF

  companion object {
    private val HEX_DIGITS = "0123456789ABCDEF".toList()

    val UNTINTED = Color(0xFF62FF00.toInt())
    val WHITE = Color(0xFFFFFFFF.toInt())
    val BLACK = Color(0xFF000000.toInt())
    val TRANSPARENT = Color(0)
  }
}
