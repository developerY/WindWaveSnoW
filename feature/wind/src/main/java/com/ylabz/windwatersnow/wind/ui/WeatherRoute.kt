package com.ylabz.windwatersnow.wind.ui

import WeatherCardsAI
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ylabz.windwatersnow.core.ui.Loading
import com.ylabz.windwatersnow.network.model.OpenWeatherResponse


@Composable
fun WeatherRoute(
    paddingValues: PaddingValues,

    navTo: (String) -> Unit,
    //navController: NavController,
    //onGoToItem: (Long) -> Unit,
    //modifier: Modifier = Modifier,
    title: String,
    viewModel: WindViewModel = hiltViewModel()
) {
    val onEvent: (WeatherEvent) -> Unit = viewModel::onEvent
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherUIScreen(
        title = title,
        paddingValues = paddingValues,
        onEvent = onEvent,
        weatherUiState = state,
        navTo = navTo,
    )
}

@Composable
internal fun WeatherUIScreen(
    modifier: Modifier = Modifier,
    title: String,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherUiState: WeatherUiState,
    navTo: (String) -> Unit,
) {
    when (weatherUiState) {
        WeatherUiState.Loading -> Loading(modifier)

        is WeatherUiState.Success -> WeatherUIContent(
            modifier,
            title = title,
            paddingValues = paddingValues,
            onEvent = onEvent,
            openWeatherResponse = weatherUiState.weather,
            navTo = navTo
        )

        is WeatherUiState.Error -> {
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                title = { Text("Error") },
                text = { Text(weatherUiState.message) },
                confirmButton = {
                    Button(onClick = { /*TODO*/ }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


@Composable
internal fun WeatherUIContent(
    modifier: Modifier = Modifier,
    title: String,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    openWeatherResponse: OpenWeatherResponse?,
    navTo: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.border(2.dp, Color.Red)
    ) {
        WeatherCardsAI(openWeatherResponse = openWeatherResponse, title)
    }
}

