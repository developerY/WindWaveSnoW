package com.ylabz.windwatersnow.wind.ui.components.surf

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.gson.Gson
import com.ylabz.windwatersnow.core.ui.Loading
import com.ylabz.windwatersnow.network.model.Clouds
import com.ylabz.windwatersnow.network.model.Coord
import com.ylabz.windwatersnow.network.model.Main
import com.ylabz.windwatersnow.network.model.Sys
import com.ylabz.windwatersnow.network.model.Weather
import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.network.model.Wind
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import com.ylabz.windwatersnow.wind.ui.WeatherUiState
import com.ylabz.windwatersnow.wind.ui.WindViewModel
import com.ylabz.windwatersnow.wind.ui.components.snow.SnowboardContent
import com.ylabz.windwatersnow.wind.ui.components.snow.SnowboardScreen

@Composable
fun SurferWindRoute(
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    //navController: NavController,
    //onGoToItem: (Long) -> Unit,
    //modifier: Modifier = Modifier,
    viewModel: WindViewModel = hiltViewModel()
) {
    val onEvent: (WeatherEvent) -> Unit = viewModel::onEvent
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SurferWindScreen(
        paddingValues = paddingValues,
        onEvent = onEvent,
        weatherUiState = state,
        navTo = navTo,
    )
}

@Composable
internal fun SurferWindScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherUiState: WeatherUiState,
    navTo: (String) -> Unit,
) {
    when (weatherUiState) {
        WeatherUiState.Loading -> Loading(modifier)

        is WeatherUiState.Success -> SurferWindContent(
            modifier,
            paddingValues = paddingValues,
            onEvent = onEvent,
            weather = weatherUiState.weather,
            navTo = navTo
        )

        WeatherUiState.Error -> TODO()
    }
}

@Composable
fun SurferWindContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weather: WeatherResponse?,
    navTo: (String) -> Unit,
) {

    Surface(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(2.dp, Color.Black) // Add border to the bottom box
            ) {
                SurfersWindCards(weather)
                WaveAnimationScreenSet()
            }
        }

    }
}


@Composable
internal fun SurfersWindCards(weather: WeatherResponse?) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SurfLocationCard(location = "Beach Break")
            Spacer(modifier = Modifier.height(16.dp))
            SurfWindSpeedCard(speed = weather?.wind?.speed ?: 0.0)
            Spacer(modifier = Modifier.height(16.dp))
            SurfWindDirectionCard(deg = weather?.wind?.deg ?: 0)
            Spacer(modifier = Modifier.height(16.dp))
            WaveHeightCard(height = 5.2)
        }
    }
}

@Composable
fun SurfLocationCard(location: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = location,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SurfWindSpeedCard(speed: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WindScreen Speed",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${speed} m/s",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Icon(
                imageVector = Icons.Default.Air,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Composable
fun SurfWindDirectionCard(deg: Int) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WindScreen Direction",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Icon(
                imageVector = Icons.Default.Navigation,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(64.dp)
                    .graphicsLayer(rotationZ = rotation)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$deg°",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WaveHeightCard(height: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Wave Height",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$height m",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Icon(
                imageVector = Icons.Default.Waves,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SurferWindContentPreview() {


    /*val weatherResponse = WeatherResponse(
        coord = Coord(12.34, 56.78),
        weather =  [],
        val base: String,
        val main: Main,
        val visibility: Int,
        val wind: Wind,
        val clouds: Clouds,
        val dt: Long,
        val sys: Sys,
        val timezone: Int,
        val id: Int,
        val name: String,
        val cod: Int
    )*/

    /*val weatherResponse = """{
                "wind": { "speed": 5.5, "deg": 240 },
                "snow": { "volume": 30 },
                "waves": { "height": 5 },
                "main": { "temp": -5 },
                "name": "Open Sea"
            }""".trimMargin()*/

    // SurferWindContent(weather = weatherResponse, paddingValues = PaddingValues())
}
