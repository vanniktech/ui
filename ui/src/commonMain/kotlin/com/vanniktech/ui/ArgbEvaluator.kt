/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanniktech.ui

import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Taken from Android and made multiplatform.
 *
 * This evaluator can be used to perform type interpolation between two [Color]s.
 */
object ArgbEvaluator {
  fun evaluate(
    fraction: Float,
    startValue: Color,
    endValue: Color,
  ): Color {
    val startA = startValue.alpha() / 255.0f
    var startR = startValue.red() / 255.0f
    var startG = startValue.green() / 255.0f
    var startB = startValue.blue() / 255.0f
    val endA = endValue.alpha() / 255.0f
    var endR = endValue.red() / 255.0f
    var endG = endValue.green() / 255.0f
    var endB = endValue.blue() / 255.0f

    // Convert from sRGB to linear.
    startR = startR.pow(2.2f)
    startG = startG.pow(2.2f)
    startB = startB.pow(2.2f)
    endR = endR.pow(2.2f)
    endG = endG.pow(2.2f)
    endB = endB.pow(2.2f)

    // Compute the interpolated color in linear space.
    var a = startA + fraction * (endA - startA)
    var r = startR + fraction * (endR - startR)
    var g = startG + fraction * (endG - startG)
    var b = startB + fraction * (endB - startB)

    // Convert back to sRGB in the [0..255] range.
    a *= 255.0f

    r = r.pow(1.0f / 2.2f) * 255.0f
    g = g.pow(1.0f / 2.2f) * 255.0f
    b = b.pow(1.0f / 2.2f) * 255.0f

    return Color(a.roundToInt() shl 24 or (r.roundToInt() shl 16) or (g.roundToInt() shl 8) or b.roundToInt())
  }
}
