package com.ylabz.windwatersnow.wind.ui.components.system

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ylabz.windwatersnow.core.ui.Permission
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalCoroutinesApi
@Composable
fun SpeechCaptureUI(
    hasPermission: Boolean,
    modifier: Modifier = Modifier,
    updateText: (String) -> Unit,
    onEvent: (WeatherEvent) -> Unit
) {
    if (hasPermission) {
        SpeechCaptureUIContent(modifier = modifier, updateText = updateText, onEvent = onEvent)
    } else {
        val context = LocalContext.current
        Permission(
            permission = Manifest.permission.RECORD_AUDIO,
            rationale = "You said you wanted speech reorganization, so I'm going to have to ask for permission.",
            permissionNotAvailableContent = {
                Column(modifier) {
                    Text("O noes! Mic!")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            context.startActivity(
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts("package", context.packageName, null)
                                }
                            )
                        }
                    ) {
                        Text("Open Settings")
                    }
                }
            }
        ) {
            SpeechCaptureUI(
                hasPermission = hasPermission,
                updateText = { desTxt -> onEvent(WeatherEvent.SetLocation(desTxt)) },
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun SpeechCaptureUIContent(
    modifier: Modifier = Modifier,
    updateText: (String) -> Unit,
    onEvent: (WeatherEvent) -> Unit
) {
    IconButton(
        onClick = {
            onEvent(WeatherEvent.StartCaptureSpeech2Txt(updateText) {})
        },
    ) {
        Icon(
            Icons.Filled.RecordVoiceOver,
            contentDescription = "voice recording",
            //tint = Color.LightGray
        )
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
@Preview
@Composable
fun SpeechCapturePreview() {
    // val localSpeechState = compositionLocalOf { SpeechState() }
    SpeechCaptureUIContent(
        modifier = Modifier,
        updateText = {},
        onEvent = {}
    )
}