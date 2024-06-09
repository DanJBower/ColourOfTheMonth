package com.danjbower.colourofthemonth.utility

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun previewableReadFileFromAssets(fileName: String): List<String> {
    val context = LocalContext.current
    return remember(fileName) {
        readFileFromAssets(context, fileName)
    }
}

fun readFileFromAssets(context: Context, fileName: String): List<String> {
    val assetManager = context.assets
    val inputStream = assetManager.open(fileName)
    return inputStream.bufferedReader().use { it.readLines() }
}
