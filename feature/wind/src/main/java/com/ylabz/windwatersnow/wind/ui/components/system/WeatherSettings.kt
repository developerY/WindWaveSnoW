package com.ylabz.windwatersnow.wind.ui.components.system

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ylabz.windwatersnow.core.ui.FeatureThatRequireMicPermission
import com.ylabz.windwatersnow.wind.ui.WindViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherSettings(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navTo: (String) -> Unit,
    addPhotoVM: WindViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val onEvent = addPhotoVM::onEvent

    val permissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    // Request permission lambda
    val requestPermission: () -> Unit = {
        permissionState.launchPermissionRequest()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF1A9121), Color(0xFF8BC34A)),
                    center = Offset(x=500f,y=0f),
                    radius = 600f
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Weather Settings",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This feature is still under development.",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFF1A9121), Color(0xFF8BC34A)),
                        //center = Offset(0.5f, 0.1f),
                        radius = 600f
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (permissionState.status.isGranted) {
                Text("Permission Granted for Mic")
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current
                    FeatureThatRequireMicPermission(
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
                        Text(
                            text = "Permission Required For Mic",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        Button(
                            onClick = { requestPermission() },
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        ) {
                            Text("Request Permission")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherSettingsPreview() {
    MaterialTheme {
        WeatherSettings(
            paddingValues = PaddingValues(),
            navTo = {}
        )
    }
}
