package com.danjbower.colourofthemonth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.danjbower.colourofthemonth.model.ColourOfTheMonthModel
import com.danjbower.colourofthemonth.utility.readFileFromAssets
import com.danjbower.colourofthemonth.view.ColourOfTheMonth
import com.danjbower.colourofthemonth.viewmodel.ColourOfTheMonthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val model = ColourOfTheMonthModel(
                readFileFromAssets(this, "colours.txt"),
                readFileFromAssets(this, "changelog.txt"),
            )
            val viewModel = ColourOfTheMonthViewModel(model)
            ColourOfTheMonth(viewModel)
        }
    }
}
