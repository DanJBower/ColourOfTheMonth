package com.danjbower.colourofthemonth.viewmodel

import androidx.lifecycle.ViewModel
import com.danjbower.colourofthemonth.data.Colour
import com.danjbower.colourofthemonth.model.ColourOfTheMonthModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
        ))
        viewState = _viewState.asStateFlow()
    }

    data class ViewState
    (
        val currentDate: String,
        val currentColourInfo: ColourInfo,
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
        return ColourInfo(
            name = colour.name,
            hex = colour.hex,
            rgb = getRGB(colour.hex),
            hsl = HSL(0,0,0),
            hsv = HSV(0,0,0),
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

    fun getHSL(colour: Colour): HSL
    {
        return HSL(0,0,0)
    }

    fun getHSV(colour: Colour): HSV
    {
        return HSV(0,0,0)
    }

    fun update(newState: ViewState) {
        _viewState.value = newState
    }
}
