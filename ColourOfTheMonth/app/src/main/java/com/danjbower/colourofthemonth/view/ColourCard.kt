package com.danjbower.colourofthemonth.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.danjbower.colourofthemonth.data.ColourInfo

@Composable
fun ColourCard(
    date: String,
    colourInfo: ColourInfo,
    showExtraDetails: Boolean,
)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val minWidth = screenWidth * 0.7f

    Column(
        modifier = Modifier
            .requiredWidthIn(min = minWidth)
            .animateContentSize()
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            text = date,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(text = colourInfo.name)

        if (showExtraDetails)
        {
            Text(text = "Hex: ${colourInfo.hex}")
            Text(text = "R: ${colourInfo.rgb.r} G: ${colourInfo.rgb.g} B: ${colourInfo.rgb.b}")
            Text(text = "H: ${colourInfo.hsl.h}° S: ${colourInfo.hsl.s}% L: ${colourInfo.hsl.l}%")
            Text(text = "H: ${colourInfo.hsv.h}° S: ${colourInfo.hsv.s}% V: ${colourInfo.hsv.v}%")
        }
    }
}
