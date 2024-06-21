package com.ylabz.windwatersnow.wind.ui.components.snow

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.gson.Gson
import com.ylabz.windwatersnow.core.ui.Loading
import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import com.ylabz.windwatersnow.wind.ui.WeatherUiState
import com.ylabz.windwatersnow.wind.ui.WindViewModel


@Composable
fun SnowboardRoute(
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    //navController: NavController,
    //onGoToItem: (Long) -> Unit,
    //modifier: Modifier = Modifier,
    viewModel: WindViewModel = hiltViewModel()
) {
    val onEvent: (WeatherEvent) -> Unit = viewModel::onEvent
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SnowboardScreen(
        paddingValues = paddingValues,
        onEvent = onEvent,
        weatherUiState = state,
        navTo = navTo,
    )
}

@Composable
fun SnowboardScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherUiState: WeatherUiState,
    navTo: (String) -> Unit,
) {
    when (weatherUiState) {
        WeatherUiState.Loading -> Loading(modifier)

        is WeatherUiState.Success -> SnowboardContent(
            modifier,
            paddingValues = paddingValues,
            onEvent = onEvent,
            weatherResponse = weatherUiState.weather,
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
fun SnowboardContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherResponse: WeatherResponse?,
    navTo: (String) -> Unit,
) {
    // fix the string!
    // use quicktype to get all the code  https://quicktype.io/
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            SnowboardCards(snowboardResponse = weatherResponse)
            SnowfallAnimation()
        }
    }
}

@Composable
internal fun SnowboardCards(snowboardResponse: WeatherResponse?) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SnowLocationCard(location = "Snowy Mountain")
            Spacer(modifier = Modifier.height(16.dp))
            SnowTemperatureCard(temp = snowboardResponse?.main?.temp ?: 0.1)
            Spacer(modifier = Modifier.height(16.dp))
            SnowWindSpeedCard(speed = snowboardResponse?.wind?.speed ?: 0.0)
            Spacer(modifier = Modifier.height(16.dp))
            SnowWindDirectionCard(deg = snowboardResponse?.wind?.deg ?: 0)
            Spacer(modifier = Modifier.height(16.dp))
            SnowVolumeCard(volume = 2.5)
        }
    }
}

@Composable
fun SnowLocationCard(location: String) {
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
fun SnowTemperatureCard(temp: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temperature",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${temp}°C",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Icon(
                imageVector = Icons.Default.AcUnit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Composable
fun SnowWindSpeedCard(speed: Double) {
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
fun SnowWindDirectionCard(deg: Int) {
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
fun SnowVolumeCard(volume: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SnowScreen Volume",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${volume} cm",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Icon(
                imageVector = Icons.Default.Cloud,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SnowboardContentPreview() {
    /*val sampleWeatherData = """{
                "wind": { "speed": 5.5, "deg": 240 },
                "snow": { "volume": 30 },
                "waves": { "height": 5 },
                "main": { "temp": -5 },
                "name": "Open Sea"
            }""".trimMargin()



    SnowboardContent(
        paddingValues = PaddingValues(),
        onEvent = {WeatherEvent -> Unit}  ,
        weather= sampleWeatherData,
        navTo = {String -> Unit}
    )*/

}

