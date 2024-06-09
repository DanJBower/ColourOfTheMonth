package com.danjbower.colourofthemonth.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    ColourOfTheMonth(viewModel)
}

@Composable
fun ColourOfTheMonth(viewModel: ColourOfTheMonthViewModel)
{
    val viewState by viewModel.viewState.collectAsState()

    ColourOfTheMonthTheme {
        Surface(modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()
                    .background(Color(viewState.currentColourInfo.rgb.r,
                        viewState.currentColourInfo.rgb.g,
                        viewState.currentColourInfo.rgb.b)),
                contentAlignment = Alignment.Center,
            )
            {
                Box(modifier = Modifier.wrapContentSize()
                    .background(MaterialTheme.colorScheme.background))
                {
                    ColourCard(

                        date = viewState.currentDate,
                        colour = viewState.currentColourInfo.name
                    )
                }
            }
        }
    }
}

@Composable
fun ColourCard(
    date: String,
    colour: String
)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val minWidth = screenWidth * 0.7f

    Column(
        modifier = Modifier.requiredWidthIn(min = minWidth)
            .padding(all = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            text = date,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(text = colour)
    }
}
