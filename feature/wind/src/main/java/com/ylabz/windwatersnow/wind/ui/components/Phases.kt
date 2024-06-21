package com.ylabz.windwatersnow.wind.ui.components

import android.Manifest
import android.content.pm.PackageManager
import com.ylabz.windwatersnow.wind.ui.components.snow.SnowboardRoute
import com.ylabz.windwatersnow.wind.ui.components.surf.SurferWindRoute
import com.ylabz.windwatersnow.wind.ui.components.wind.SailorWindRoute

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ylabz.windwatersnow.core.ui.Loading
import com.ylabz.windwatersnow.network.model.WeatherResponse
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import com.ylabz.windwatersnow.wind.ui.WeatherUiState
import com.ylabz.windwatersnow.wind.ui.WindViewModel
import com.ylabz.windwatersnow.wind.ui.components.system.SpeechCaptureUI
import com.ylabz.windwatersnow.wind.ui.components.wind.SailorWindContent
import com.ylabz.windwatersnow.wind.ui.components.wind.SailorWindScreen


@Composable
fun SwipeableViewsRoute(
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    //navController: NavController,
    //onGoToItem: (Long) -> Unit,
    //modifier: Modifier = Modifier,
    viewModel: WindViewModel = hiltViewModel()
) {
    val onEvent: (WeatherEvent) -> Unit = viewModel::onEvent
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SwipeableViewsScreen(
        paddingValues = paddingValues,
        onEvent = onEvent,
        weatherUiState = state,
        navTo = navTo,
    )
}

@Composable
internal fun SwipeableViewsScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherUiState: WeatherUiState,
    navTo: (String) -> Unit,
) {
    when (weatherUiState) {
        WeatherUiState.Loading -> Loading(modifier)

        is WeatherUiState.Success -> SwipeableViewsContent(
            modifier,
            paddingValues = paddingValues,
            onEvent = onEvent,
            weatherResponse = weatherUiState.weather,
            location = weatherUiState.location,
            navTo = navTo
        )

        is WeatherUiState.Error -> {
            AlertDialog(
                onDismissRequest = {  onEvent(WeatherEvent.DismissError)  },
                title = { Text("Error") },
                text = { Text(weatherUiState.message) },
                confirmButton = {
                    Button(onClick = {  onEvent(WeatherEvent.DismissError)   }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableViewsContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit,
    weatherResponse: WeatherResponse?,
    location: String,
    navTo: (String) -> Unit,
) {
    val context = LocalContext.current
    val hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    var isVisible by remember { mutableStateOf(true) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Set📍Location 🗺️")
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(
                    imageVector = if (isVisible) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (isVisible) "Hide" else "Show"
                )
            }
        }
        if (isVisible) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                // horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = location, // photodoTxtFV,
                    onValueChange = { descripText ->
                        onEvent(WeatherEvent.SetLocation(descripText))
                    },// { photodoTitle = it }, // TextFieldValue
                    label = { Text("Photodo Description") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                )
                SpeechCaptureUI(
                    hasPermission = hasPermission,
                    updateText = { desTxt -> onEvent(WeatherEvent.SetLocation(desTxt)) },
                    onEvent = onEvent
                )
            }
        }
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        val pagerState = rememberPagerState(pageCount = { 3 })
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> SailorWindRoute(paddingValues, navTo)
                1 -> SurferWindRoute(paddingValues, navTo)
                2 -> SnowboardRoute(paddingValues, navTo)
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 7.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.Gray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(9.dp)
                )
            }
        }
        // [END android_compose_pager_indicator]
        }
    }
}