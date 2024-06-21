package com.ylabz.windwatersnow.wind.ui.components.wind

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.gson.Gson
import com.ylabz.windwatersnow.core.ui.Loading
import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import com.ylabz.windwatersnow.wind.ui.WeatherUiState
import com.ylabz.windwatersnow.wind.ui.WindViewModel
import com.ylabz.windwatersnow.wind.ui.components.snow.SnowboardContent
import com.ylabz.windwatersnow.wind.ui.components.snow.SnowboardScreen
import com.ylabz.windwatersnow.wind.ui.components.system.SpeechCaptureUI


@Composable
fun SailorWindRoute(
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    //navController: NavController,
    //onGoToItem: (Long) -> Unit,
    //modifier: Modifier = Modifier,
    viewModel: WindViewModel = hiltViewModel()
) {
    val onEvent: (WeatherEvent) -> Unit = viewModel::onEvent
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SailorWindScreen(
        paddingValues = paddingValues,
        onEvent = onEvent,
        weatherUiState = state,
        navTo = navTo,
    )
}

@Composable
internal fun SailorWindScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherUiState: WeatherUiState,
    navTo: (String) -> Unit,
) {
    when (weatherUiState) {
        WeatherUiState.Loading -> Loading(modifier)

        is WeatherUiState.Success -> SailorWindContent(
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
internal fun SailorWindContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherResponse: WeatherResponse?,
    navTo: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            //.padding(paddingValues)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box {
            SailorsWindCards(weatherResponse = weatherResponse)
            WindAnimation()
        }
    }
}


@Composable
internal fun SailorsWindCards(weatherResponse: WeatherResponse?) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
            //.padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WindLocationCard(location = "Open Sea")
            Spacer(modifier = Modifier.height(16.dp))
            WindSpeedCard(weatherResponse?.wind?.speed ?: 0.0)
            Spacer(modifier = Modifier.height(16.dp))
            WindDirectionCard(deg = weatherResponse?.wind?.deg ?: 0)
            WindAnimation()
        }
    }
}

@Composable
fun WindLocationCard(location: String) {
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
fun WindSpeedCard(speed: Double) {
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
fun WindDirectionCard(deg: Int) {
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

@Preview
@Composable
private fun SailorWindContent() {
    SailorWindContent()
}


