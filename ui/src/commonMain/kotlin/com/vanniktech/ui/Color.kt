package com.vanniktech.ui

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.jvm.JvmInline
import kotlin.math.ceil

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

internal val COLOR_COMPONENT_RANGE = 0..255
internal val FLOAT_VALUE = COLOR_COMPONENT_RANGE.last.toFloat()
internal const val HEX_PREFIX = "#"

@Serializable(ColorSerializer::class)
@UiParcelize @JvmInline value class Color(val argb: Int) : UiParcelable {
  override fun toString(): String {
    var number = argb
    var buffer = ""

    repeat(if (alpha() == COLOR_COMPONENT_RANGE.last) 6 else 8) {
      buffer += HEX_DIGITS[number and 0xF]
      number = number ushr 4
    }

    return "$HEX_PREFIX${buffer.reversed()}"
  }

  internal fun hexString() = toString().removePrefix(HEX_PREFIX)
  fun htmlRgbaString() = "rgba(${red()}, ${green()}, ${blue()}, ${alpha()})"

  fun shouldUseBlackFont(): Boolean {
    val lum = 0.299 * red() + (0.587 * green() + 0.114 * blue())
    return lum > 186
  }

  internal fun alpha() = argb shr 24 and 0xFF
  internal fun red() = argb shr 16 and 0xFF
  internal fun green() = argb shr 8 and 0xFF
  internal fun blue() = argb and 0xFF

  fun copy(
    alpha: Int = alpha(),
    red: Int = red(),
    green: Int = green(),
    blue: Int = blue(),
  ) = fromArgb(
    alpha = alpha,
    red = red,
    green = green,
    blue = blue,
  )

  fun copy(
    alpha: Float = alpha() / FLOAT_VALUE,
    red: Float = red() / FLOAT_VALUE,
    green: Float = green() / FLOAT_VALUE,
    blue: Float = blue() / FLOAT_VALUE,
  ) = fromArgb(
    alpha = ceil(alpha * FLOAT_VALUE).toInt(),
    red = ceil(red * FLOAT_VALUE).toInt(),
    green = ceil(green * FLOAT_VALUE).toInt(),
    blue = ceil(blue * FLOAT_VALUE).toInt(),
  )

  /** Will return a new [Color] which is darker when [factor] is below 1 and brighter if [factor] is above 1. */
  fun brighten(factor: Float) = fromArgb(
    alpha = alpha(),
    red = (red() * factor).toInt().coerceIn(COLOR_COMPONENT_RANGE),
    green = (green() * factor).toInt().coerceIn(COLOR_COMPONENT_RANGE),
    blue = (blue() * factor).toInt().coerceIn(COLOR_COMPONENT_RANGE),
  )

  companion object {
    internal val HEX_DIGITS = "0123456789ABCDEF".toList()

    val UNTINTED = Color(0xFF62FF00.toInt())
    val WHITE = Color(0xFFFFFFFF.toInt())
    val BLACK = Color(0xFF000000.toInt())
    val TRANSPARENT = Color(0)

    fun fromArgb(
      alpha: Int,
      red: Int,
      green: Int,
      blue: Int,
    ): Color {
      require(alpha in COLOR_COMPONENT_RANGE) { "alpha \"$alpha\" is not in \"$COLOR_COMPONENT_RANGE\" range" }
      require(red in COLOR_COMPONENT_RANGE) { "red \"$red\" is not in \"$COLOR_COMPONENT_RANGE\" range" }
      require(green in COLOR_COMPONENT_RANGE) { "green \"$green\" is not in \"$COLOR_COMPONENT_RANGE\" range" }
      require(blue in COLOR_COMPONENT_RANGE) { "blue \"$blue\" is not in \"$COLOR_COMPONENT_RANGE\" range" }
      return Color(alpha shl 24 or (red shl 16) or (green shl 8) or blue)
    }

    fun fromHexOrNull(hex: String): Color? {
      val cleaned = hex.removePrefix(HEX_PREFIX)
      val string = when (cleaned.length) {
        3 -> "FF${cleaned.map { "$it$it" }.joinToString(separator = "")}"
        4 -> cleaned.map { "$it$it" }.joinToString(separator = "")
        6 -> "FF$cleaned"
        8 -> cleaned
        else -> null
      }

      return try {
        string?.toLong(radix = 16)?.toInt()?.let(::Color)
      } catch (throwable: Throwable) {
        null
      }
    }
  }
}
