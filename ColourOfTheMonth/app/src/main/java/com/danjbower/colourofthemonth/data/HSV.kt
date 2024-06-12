package com.danjbower.colourofthemonth.data

import android.graphics.Color
import kotlin.math.roundToInt

data class HSV
(
    val h: Int,
    val s: Int,
    val v: Int,
)


fun getHSV(rgb: RGB): HSV
{
    var arr = FloatArray(3)
    Color.RGBToHSV(rgb.r, rgb.g, rgb.b, arr)
    return HSV(
        arr[0].roundToInt(),
        (arr[1] * 100).roundToInt(),
        (arr[2] * 100).roundToInt(),
    )
}
