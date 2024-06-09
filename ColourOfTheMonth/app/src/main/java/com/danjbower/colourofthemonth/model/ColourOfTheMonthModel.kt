package com.danjbower.colourofthemonth.model

import com.danjbower.colourofthemonth.data.Colour
import com.danjbower.colourofthemonth.data.History
import java.io.File

class ColourOfTheMonthModel
{
    private val colours : List<Colour> = parseColourList()
    val changelog : List<History> = parseChangelog()
    val currentColour = changelog[0]

    fun parseColourList(): List<Colour>
    {
        return File("colours.csv").readLines()
            .drop(1)
            .map {line ->
                val lineInfo = line.split(",")
                Colour(name = lineInfo[0], hex = lineInfo[1])
            }
    }

    fun parseChangelog(): List<History>
    {
        return File("colours.csv").readLines()
            .drop(1)
            .map {line ->
                val lineInfo = line.split(",")
                History(date = lineInfo[0], colour = colours.first { colour -> colour.name == lineInfo[1] })
            }
    }
}
