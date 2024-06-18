package com.ylabz.windwatersnow.wind.ui.components.system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Locations(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
) {
    Column (
        modifier = Modifier.padding(paddingValues)
    ) {
        Text("Locations")
    }
}