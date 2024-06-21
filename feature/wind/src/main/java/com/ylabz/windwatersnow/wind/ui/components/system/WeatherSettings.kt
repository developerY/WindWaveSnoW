package com.ylabz.windwatersnow.wind.ui.components.system

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import com.ylabz.windwatersnow.wind.ui.WindViewModel

@Composable
fun WeatherSettings(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    addPhotoVM: WindViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    val onEvent = addPhotoVM::onEvent
    Column (
        modifier = Modifier.padding(paddingValues)
    ) {
        Row() {
            Text("Set Location")
            SpeechCaptureUI(
                hasPermission = hasPermission,
                updateText = { desTxt -> onEvent(WeatherEvent.SetLocation(desTxt)) },
                onEvent = onEvent
            )
        }
    }
}