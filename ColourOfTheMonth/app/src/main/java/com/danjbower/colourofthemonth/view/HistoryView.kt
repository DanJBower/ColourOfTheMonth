package com.danjbower.colourofthemonth.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danjbower.colourofthemonth.data.Colour
import com.danjbower.colourofthemonth.data.History
import com.danjbower.colourofthemonth.ui.theme.ColourOfTheMonthTheme
import com.danjbower.colourofthemonth.viewmodel.HistoryViewModel

@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun HistoryCardPreview(expand: Boolean = false)
{
    val history = History(
        "June 2024",
        Colour(
            "Red",
            "#F00000"
        ),
    )
    val viewModel = HistoryViewModel(history)

    if (expand)
    {
        viewModel.toggleInfoDetail()
    }

    ColourOfTheMonthTheme {
        HistoryCard(viewModel)
    }
}

@Preview(
    name = "Dark Mode Expanded",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun ExpandedHistoryCardPreview()
{
    HistoryCardPreview(true)
}

@Composable
fun HistoryCard(viewModel: HistoryViewModel)
{
    val viewState by viewModel.viewState.collectAsState()

    Surface(modifier = Modifier
        .wrapContentSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    viewModel.toggleInfoDetail()
                }
            )
        },
    ) {
        Box(modifier = Modifier
            .background(
                Color(
                    viewState.currentColourInfo.rgb.r,
                    viewState.currentColourInfo.rgb.g,
                    viewState.currentColourInfo.rgb.b
                )
            )
            .padding(10.dp),
            contentAlignment = Alignment.Center,
        )
        {
            Box(modifier = Modifier
                .wrapContentSize()
                .shadow(8.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.background),
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
