package com.danjbower.colourofthemonth.data

data class ColourInfo
(
    val name: String,
    val hex: String,
    val rgb: RGB,
    val hsl: HSL,
    val hsv: HSV,
)


fun getColourInfo(colour: Colour): ColourInfo
{
    val rgb = getRGB(colour.hex)
    return ColourInfo(
        name = colour.name,
        hex = colour.hex,
        rgb = rgb,
        hsl = getHSL(rgb),
        hsv = getHSV(rgb),
    )
}