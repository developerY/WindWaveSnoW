package com.ylabz.windwatersnow.core.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FeatureThatRequireMicPermission(
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {

    // Mic permission state
    val micPermissionState = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )

    if (micPermissionState.status.isGranted) {
        //content()
    } else {
        Column {
            val textToShow = if (micPermissionState.status.shouldShowRationale) {
                // If the user has denied the permission but the rationale can be shown,
                // then gently explain why the app requires this permission
                "The Mic is important for this app. Please grant the permission."
            } else {
                // If it's the first time the user lands on this feature, or the user
                // doesn't want to be asked again for this permission, explain that the
                // permission is required
                "Mic permission required for this feature to be available. " +
                        "Please grant the permission"
            }
            Text(textToShow)
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = { micPermissionState.launchPermissionRequest() }
            ) {
                Text("Request Mic Permission")
            }
        }
    }
}
