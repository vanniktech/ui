package com.vanniktech.ui.theming

import com.vanniktech.ui.Color
import com.vanniktech.ui.UiParcelable
import com.vanniktech.ui.UiParcelize
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

object ThemingColorSerializer : KSerializer<ThemingColor> {
  override val descriptor: SerialDescriptor
    get() = buildClassSerialDescriptor("ThemingColor") {
      element<Color>("light")
      element<Color>("dark")
    }

  @OptIn(ExperimentalSerializationApi::class)
  override fun deserialize(decoder: Decoder) =
    decoder.decodeStructure(descriptor) {
      if (decodeSequentially()) {
        ThemingColor(
          light = decodeSerializableElement(descriptor, 0, Color.serializer()),
          dark = decodeSerializableElement(descriptor, 1, Color.serializer()),
        )
      } else {
        var light: Color? = null
        var dark: Color? = null

        while (true) {
          when (val index = decodeElementIndex(descriptor)) {
            0 -> light = decodeSerializableElement(descriptor, index, Color.serializer())
            1 -> dark = decodeSerializableElement(descriptor, index, Color.serializer())
            CompositeDecoder.DECODE_DONE -> break
            else -> error("Unexpected index: $index")
          }
        }

        ThemingColor(
          light = requireNotNull(light),
          dark = requireNotNull(dark),
        )
      }
    }

  override fun serialize(encoder: Encoder, value: ThemingColor) {
    encoder.encodeStructure(descriptor) {
      encodeSerializableElement(descriptor, 0, Color.serializer(), value.light)
      encodeSerializableElement(descriptor, 1, Color.serializer(), value.dark)
    }
  }
}

@Serializable(ThemingColorSerializer::class)
@UiParcelize data class ThemingColor(
  val light: Color,
  val dark: Color,
) : UiParcelable {
  fun with(alpha: Float) = ThemingColor(
    light = light.copy(alpha = alpha),
    dark = dark.copy(alpha = alpha),
  )

  fun mapped(isNight: Boolean) = when (isNight) {
    true -> dark
    else -> light
  }

  override fun toString() = "Light: $light, Dark: $dark"

  companion object {
    fun single(color: Color) = ThemingColor(
      light = color,
      dark = color,
    )
  }
}
