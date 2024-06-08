package com.ylabz.windwatersnow.feature.wear.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Text
import com.ylabz.windwatersnow.data.mapper.windwatersnow


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state, modifier)
}

@Composable
internal fun HomeScreen(
    state: HomeUiState,
    modifier: Modifier = Modifier
) {
    when(state) {
        HomeUiState.Loading -> QuickLoad() //Loading(modifier)
        is HomeUiState.Success -> Content(state.data, modifier)
    }
}

@Composable
internal fun Content(
    items: List<windwatersnow>,
    modifier: Modifier = Modifier
) {
    ScalingLazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = items) { item ->
            windwatersnowChip(windwatersnow = item, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun windwatersnowChip(windwatersnow: windwatersnow, modifier: Modifier = Modifier) {
    // No Box or Row needed
    Chip(
        label = { Text(text = windwatersnow.title) },
        onClick = { /* no-op */ },
        modifier = modifier.fillMaxWidth()
    )
}


@Composable
fun QuickLoad() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .background(Color.Blue) // Set the background color to blue
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Loading")
    }
}
