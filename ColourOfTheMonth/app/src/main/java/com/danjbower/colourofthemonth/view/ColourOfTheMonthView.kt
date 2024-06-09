package com.danjbower.colourofthemonth.view

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danjbower.colourofthemonth.model.ColourOfTheMonthModel
import com.danjbower.colourofthemonth.ui.theme.ColourOfTheMonthTheme
import com.danjbower.colourofthemonth.utility.previewableReadFileFromAssets
import com.danjbower.colourofthemonth.viewmodel.ColourOfTheMonthViewModel

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun ColourOfTheMonthPreview()
{
    val model = ColourOfTheMonthModel(
        previewableReadFileFromAssets("colours.csv"),
        previewableReadFileFromAssets("changelog.csv"),
    )
    val viewModel = ColourOfTheMonthViewModel(model)
    viewModel.toggleInfoDetail()
    ColourOfTheMonth(viewModel)
}

@Composable
fun ColourOfTheMonth(viewModel: ColourOfTheMonthViewModel)
{
    val viewState by viewModel.viewState.collectAsState()

    ColourOfTheMonthTheme {
        Surface(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        viewModel.toggleInfoDetail()
                    }
                )
            },
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        viewState.currentColourInfo.rgb.r,
                        viewState.currentColourInfo.rgb.g,
                        viewState.currentColourInfo.rgb.b
                    )
                ),
                contentAlignment = Alignment.Center,
            )
            {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .shadow(8.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.background)
                )
                {
                    ColourCard(
                        date = viewState.currentDate,
                        colourInfo = viewState.currentColourInfo,
                        showExtraDetails = viewState.showExtraDetail,
                    )
                }
            }
        }
    }
}

@Composable
fun ColourCard(
    date: String,
    colourInfo: ColourOfTheMonthViewModel.ColourInfo,
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