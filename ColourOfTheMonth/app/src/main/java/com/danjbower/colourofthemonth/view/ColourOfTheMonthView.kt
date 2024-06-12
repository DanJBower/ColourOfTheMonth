package com.danjbower.colourofthemonth.view

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
    viewModel.toggleChangelog()
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
                    },
                    onLongPress = {
                        viewModel.toggleChangelog()
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
                    .animateContentSize(),
                    contentAlignment = Alignment.Center,
                )
                {
                    if (viewState.showChangelog)
                    {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            viewState.changeLog.forEach { historyViewModel ->
                                HistoryCard(historyViewModel)
                            }
                        }
                    }
                    else
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
}
