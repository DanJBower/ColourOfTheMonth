package com.danjbower.colourofthemonth.viewmodel

import com.danjbower.colourofthemonth.data.Colour
import com.danjbower.colourofthemonth.model.ColourOfTheMonthModel

class ColourOfTheMonthViewModel : ViewModel
{
    private val _model: ColourOfTheMonthModel
    var currentDate : String
    var currentColour : Colour

    constructor(model: ColourOfTheMonthModel)
    {
        _model = model
        currentDate = _model.currentColour.date
        currentColour = _model.currentColour.colour
    }
}
