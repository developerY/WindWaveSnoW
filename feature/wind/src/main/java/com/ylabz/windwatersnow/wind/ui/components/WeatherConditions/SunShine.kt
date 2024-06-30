package com.ylabz.windwatersnow.wind.ui.components.WeatherConditions


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ylabz.windwatersnow.core.ui.Loading
import com.ylabz.windwatersnow.network.model.OpenWeatherResponse
import com.ylabz.windwatersnow.wind.ui.WeatherEvent
import com.ylabz.windwatersnow.wind.ui.WeatherUiState
import com.ylabz.windwatersnow.wind.ui.WindViewModel

@Composable
fun SunshineAnimationScreen() {
    val infiniteTransition = rememberInfiniteTransition()

    // Animation for the rotation angle of the rays
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotAng"
    )

    // Animation for the scaling factor of the rays (oscillating in and out)
    val scaleFactor by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "scaleFac"
    )

    // Breakout rays' initial direction
    //var breakoutDirection by remember { mutableStateOf(1f) }

    // Animation for the breakout rays traveling across the screen
    val breakoutDistance by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "breakoutDis"
    )

    // Reflect direction when hitting the edge
    /*LaunchedEffect(breakoutDistance) {
        breakoutDirection *= if (breakoutDistance == 10f) -1f else 1f
    }*/


    Sunshine(
        modifier = Modifier.fillMaxSize(),
        rotationAngle = rotationAngle,
        scaleFactor = scaleFactor,
        breakoutDistance = breakoutDistance,
        //breakoutDirection = breakoutDirection
    )

}

@Composable
fun Sunshine(
    modifier: Modifier = Modifier,
    rotationAngle: Float,
    scaleFactor: Float,
    breakoutDistance: Float,
    //breakoutDirection: Float
) {
    Canvas(modifier = modifier) {
        val sunRadius = size.minDimension / 8
        val sunCenter = Offset(x = sunRadius * 1.5f, y = sunRadius * 1.5f)

        // Draw Sun
        drawCircle(
            color = Color.Yellow,
            radius = sunRadius,
            center = sunCenter
        )

        // Draw Sun Rays
        for (i in 0 until 12) {
            rotate(degrees = rotationAngle + i * 30, pivot = sunCenter) {
                val isBreakoutRay = i % 3 == 0 // Select every third ray to be a breakout ray
                if (isBreakoutRay) {
                    val rayEndY = sunCenter.y + breakoutDistance //* breakoutDirection
                    drawRoundRect(
                        color = Color.Yellow.copy(alpha = 0.8f),
                        topLeft = Offset(sunCenter.x - 5.dp.toPx(), sunCenter.y - sunRadius * 1.5f),
                        size = androidx.compose.ui.geometry.Size(10.dp.toPx(), sunRadius * 2),
                        cornerRadius = CornerRadius(5.dp.toPx())
                    )
                    drawRoundRect(
                        color = Color.Yellow.copy(alpha = 0.8f),
                        topLeft = Offset(sunCenter.x - 5.dp.toPx(), rayEndY - sunRadius * 1.5f),
                        size = androidx.compose.ui.geometry.Size(10.dp.toPx(), sunRadius * 2),
                        cornerRadius = CornerRadius(5.dp.toPx())
                    )
                } else {
                    val rayLength = sunRadius * 2 * scaleFactor
                    drawRoundRect(
                        color = Color.Yellow.copy(alpha = 0.8f),
                        topLeft = Offset(sunCenter.x - 5.dp.toPx(), sunCenter.y - sunRadius * 1.5f),
                        size = androidx.compose.ui.geometry.Size(10.dp.toPx(), rayLength),
                        cornerRadius = CornerRadius(5.dp.toPx())
                    )
                }
            }
        }
    }
}


@Preview(backgroundColor = 0xFF05043B)
@Composable
private fun SunshineAnimationScreenPreview() {
    SunshineAnimationScreen()
}