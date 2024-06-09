package com.danjbower.colourofthemonth.model

import com.danjbower.colourofthemonth.data.Colour
import com.danjbower.colourofthemonth.data.History

class ColourOfTheMonthModel
{
    private val colours : List<Colour>
    val changelog : List<History>
    val currentChange: History

    constructor(colourLines: List<String>, changelogLines: List<String>)
    {
        colours = parseColourList(colourLines)
        changelog = parseChangelog(changelogLines)
        currentChange = changelog[0]
    }

    fun parseColourList(lines: List<String>): List<Colour>
    {
        return lines
            .drop(1)
            .map {line ->
                val lineInfo = line.split(",")
                Colour(name = lineInfo[0], hex = lineInfo[1])
            }
    }

    fun parseChangelog(lines: List<String>): List<History>
    {
        return lines
            .drop(1)
            .map {line ->
                val lineInfo = line.split(",")
                History(date = lineInfo[0], colour = colours.first { colour -> colour.name == lineInfo[1] })
            }
    }
}
