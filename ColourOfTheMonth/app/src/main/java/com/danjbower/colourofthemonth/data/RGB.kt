package com.danjbower.colourofthemonth.data

data class RGB
(
    val r: Int,
    val g: Int,
    val b: Int,
)


fun getRGB(hex: String): RGB
{
    return RGB (
        r = hex.substring(1, 3).toInt(16),
        g = hex.substring(3, 5).toInt(16),
        b = hex.substring(5, 7).toInt(16),
    )
}
