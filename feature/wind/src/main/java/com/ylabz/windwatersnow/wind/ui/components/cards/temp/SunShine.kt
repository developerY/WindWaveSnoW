package com.ylabz.windwatersnow.wind.ui.components.cards.temp


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
                val rayWidth = 1.dp.toPx() // Make the rays thinner
                if (isBreakoutRay) {
                    val rayEndY = sunCenter.y + breakoutDistance //* breakoutDirection
                    drawRoundRect(
                        color = Color.Yellow.copy(alpha = 0.8f),
                        topLeft = Offset(sunCenter.x - rayWidth / 2, sunCenter.y - sunRadius * 4f),
                        size = androidx.compose.ui.geometry.Size(rayWidth, sunRadius * 2),
                        cornerRadius = CornerRadius(rayWidth / 2)
                    )
                    drawRoundRect(
                        color = Color.Yellow.copy(alpha = 0.8f),
                        topLeft = Offset(sunCenter.x - rayWidth / 2, rayEndY - sunRadius * 4f),
                        size = androidx.compose.ui.geometry.Size(rayWidth, sunRadius * 2) * 2f,
                        cornerRadius = CornerRadius(rayWidth / 2)
                    )
                } else {
                    val rayLength = sunRadius * 2f * scaleFactor
                    drawRoundRect(
                        color = Color.Yellow.copy(alpha = 0.8f),
                        topLeft = Offset(sunCenter.x - rayWidth / 2, sunCenter.y - sunRadius * 4f),
                        size = androidx.compose.ui.geometry.Size(rayWidth, rayLength) * 2f,
                        cornerRadius = CornerRadius(rayWidth / 2)
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