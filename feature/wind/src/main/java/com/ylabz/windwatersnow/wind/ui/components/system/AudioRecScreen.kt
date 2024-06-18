package com.ylabz.windwatersnow.wind.ui.components.system

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ylabz.windwatersnow.core.ui.Permission
import com.ylabz.windwatersnow.feature.wind.R
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AudioRecScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    onEvent: (WeatherEvent) -> Unit,
) {
    //val scaffoldState = rememberScaffoldState()
    // Scaffold {paddingVals ->
    val context = LocalContext.current
    //NOTE: Permission Logic is here
    //TODO: Permission not granted needs work
    Permission(
        //modifier = modifier.padding(paddingValues),
        permission = Manifest.permission.RECORD_AUDIO,
        rationale = "You said you wanted to make an audio recording, so I'm going to have to ask for permission.",
        permissionNotAvailableContent = {
            Column(modifier) {
                Text("O noes! No Mic!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    })
                })
                { Text("Open Settings") }
            }
        }
    ) {
        AudioRecScreenContentAI(paddingValues,onEvent)
    }
    //}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AudioRecScreenContentAI(
    paddingValues: PaddingValues,
    onEvent: (WeatherEvent) -> Unit
) {
    var isRecording by remember { mutableStateOf(false) }
    var seconds by remember { mutableStateOf(0) }
    var recordingColor = if (isRecording) Color.Red else MaterialTheme.colorScheme.primary
    val coroutineScope = rememberCoroutineScope()


    val pulsatingScale by animateFloatAsState(
        targetValue = if (isRecording) 1.2f else 1.0f,
        animationSpec = tween(durationMillis = 500)
    )

    // Add a progress bar to indicate recording progress
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(isRecording) {
        while (isRecording) {
            progress += 0.1f
            if (progress >= 1f) {
                progress = 0f
            }
            seconds++
            delay(100L)
        }
    }

    // Add a visual cue to indicate the recording status


    // Enhance the button style with a ripple effect
    //val rippleEffect by remember { mutableStateOf(Ripple.clickable()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isRecording) Color.Red else White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "background image",
            //alpha = 0.2F,
            modifier = Modifier
                .padding(start = 25.dp)
                .fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .align(Alignment.Center)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize() // Set your desired size here
                    .padding(16.dp), // Optional padding
                color = Color.Black,
                progress = progress,
                strokeWidth = 4.dp
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Record time: ${seconds} seconds",
                style = MaterialTheme.typography.bodyMedium,
                color = White
            )

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                //.clickable(ripple = rippleEffect) { isRecording = !isRecording }
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .offset(y = (-40.dp) * pulsatingScale)
                        .scale(pulsatingScale),
                    onClick = {
                        isRecording = !isRecording
                        if (isRecording) {
                            Log.d("Photodo", "Start recoding")
                            // Start the audio recording
                            onEvent(WeatherEvent.GetWeather)
                        }
                        if (!isRecording) {
                            onEvent(WeatherEvent.GetWeather)
                            //TODO: Drop the bar
                            //And save audio recording to a file
                        }
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = recordingColor)
                ) {
                    Icon(
                        if (isRecording) Icons.Filled.StopCircle else Icons.Filled.MusicNote,
                        contentDescription = if (isRecording) "Stop Recording" else "Start Audio",
                        modifier = Modifier.size(32.dp),
                        tint = White
                    )
                }
            }
        }
    }
}


@Composable
private fun AudioRecScreenContent() {
    var isRecording by remember { mutableStateOf(false) }
    var seconds by remember { mutableStateOf(0) }
    val pulsatingScale by animateFloatAsState(
        targetValue = if (isRecording) 1.2f else 1.0f,
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(isRecording) {
        while (isRecording) {
            delay(1000L)
            seconds++
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "background image",
            alpha = 0.2F,
            modifier = Modifier
                .padding(start = 25.dp)
                .fillMaxSize(),
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Record time: ${seconds} seconds")
            Row {
                OutlinedButton(
                    modifier = Modifier
                        .offset(y = (-40.dp) * pulsatingScale)
                        .scale(pulsatingScale),
                    //contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp),

                    onClick = { isRecording = !isRecording },

                    //startRecording() ViewModel ( event )
                    // onEvent(RecAudioEvent.StartRecording)
                    //recAudioViewModel.onEvent(RecAudioEvent.StartRecording)
                ) {
                    Icon(
                        if (isRecording) Icons.Filled.StopCircle else Icons.Filled.MusicNote,
                        contentDescription = if (isRecording) "Stop Recording" else "Start Audio",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "AI")
@Composable
private fun AudioRecScreenPreviewAI() {
    val onEvent: (WeatherEvent) -> Unit = {}
    val scaffoldStatePreview = rememberBottomSheetScaffoldState()
    AudioRecScreenContentAI(PaddingValues(), onEvent)
}

@Preview
@Composable
private fun AudioRecScreenPreview() {
    val onEvent: (WeatherEvent) -> Unit = {}
    AudioRecScreenContent()
}

