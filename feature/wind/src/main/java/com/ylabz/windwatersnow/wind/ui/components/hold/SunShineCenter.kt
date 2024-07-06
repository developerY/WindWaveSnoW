package com.ylabz.windwatersnow.wind.ui.components.hold

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
fun SunshineCenterAnimationScreenHOLD() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)) {
        SunshineCenter(modifier = Modifier.fillMaxSize(), rotationAngle = rotationAngle)
    }
}

@Composable
fun SunshineCenter(modifier: Modifier = Modifier, rotationAngle: Float) {
    Canvas(modifier = modifier) {
        val sunRadius = size.minDimension / 6
        val center = Offset(x = size.width / 2, y = size.height / 2)

        // Draw Sun
        drawCircle(
            color = Color.Yellow,
            radius = sunRadius,
            center = center
        )

        // Draw Sun Rays
        for (i in 0 until 12) {
            rotate(degrees = rotationAngle + i * 30, pivot = center) {
                drawRoundRect(
                    color = Color.Yellow.copy(alpha = 0.8f),
                    topLeft = Offset(center.x - 5.dp.toPx(), center.y - sunRadius * 1.5f),
                    size = androidx.compose.ui.geometry.Size(10.dp.toPx(), sunRadius / 2),
                    cornerRadius = CornerRadius(5.dp.toPx())
                )
            }
        }
    }
}

@Preview
@Composable
private fun SunshineCenterAnimationScreenPreview() {
    SunshineCenterAnimationScreenHOLD()
}