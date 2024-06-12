package com.danjbower.colourofthemonth.data

import androidx.core.graphics.ColorUtils
import kotlin.math.roundToInt

data class HSL
(
    val h: Int,
    val s: Int,
    val l: Int,
)


fun getHSL(rgb: RGB): HSL
{
    var arr = FloatArray(3)
    ColorUtils.RGBToHSL(rgb.r, rgb.g, rgb.b, arr)
    return HSL(
        arr[0].roundToInt(),
        (arr[1] * 100).roundToInt(),
        (arr[2] * 100).roundToInt(),
    )
}
