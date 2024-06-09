package com.danjbower.colourofthemonth.viewmodel

import android.graphics.Color
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.ViewModel
import com.danjbower.colourofthemonth.data.Colour
import com.danjbower.colourofthemonth.model.ColourOfTheMonthModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.roundToInt

class ColourOfTheMonthViewModel : ViewModel
{
    private val _model: ColourOfTheMonthModel
    private val _viewState: MutableStateFlow<ViewState>
    val viewState: StateFlow<ViewState>

    constructor(model: ColourOfTheMonthModel)
    {
        _model = model
        _viewState = MutableStateFlow(ViewState(
            currentDate = _model.currentChange.date,
            currentColourInfo = getColourInfo(_model.currentChange.colour),
            showExtraDetail = false,
        ))
        viewState = _viewState.asStateFlow()
    }

    data class ViewState
    (
        val currentDate: String,
        val currentColourInfo: ColourInfo,
        val showExtraDetail: Boolean,
    )

    data class ColourInfo
    (
        val name: String,
        val hex: String,
        val rgb: RGB,
        val hsl: HSL,
        val hsv: HSV,
    )

    data class RGB
    (
        val r: Int,
        val g: Int,
        val b: Int,
    )

    data class HSL
    (
        val h: Int,
        val s: Int,
        val l: Int,
    )

    data class HSV
    (
        val h: Int,
        val s: Int,
        val v: Int,
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

    fun getRGB(hex: String): RGB
    {
        return RGB (
            r = hex.substring(1, 3).toInt(16),
            g = hex.substring(3, 5).toInt(16),
            b = hex.substring(5, 7).toInt(16),
        )
    }

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

    fun toggleInfoDetail() {
        val state = _viewState.value;
        _viewState.value = state.copy(
            showExtraDetail = !state.showExtraDetail
        )
    }
}
