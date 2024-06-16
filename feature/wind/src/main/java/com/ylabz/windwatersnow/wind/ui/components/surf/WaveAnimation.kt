package com.ylabz.windwatersnow.wind.ui.components.surf

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.sin


@Composable
fun WaveAnimationScreen(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    Box(modifier = modifier) {
        Wave(modifier = modifier, waveOffset = waveOffset)
    }
}

@Composable
fun Wave(modifier: Modifier = Modifier, waveOffset: Float) {
    Canvas(modifier = modifier) {
        val waveHeight = size.height / 10
        val waveLength = size.width / 1.5f

        drawIntoCanvas { canvas ->
            translate(top = size.height / 2) {
                val path = androidx.compose.ui.graphics.Path().apply {
                    moveTo(0f, waveHeight)
                    for (x in 0..size.width.toInt()) {
                        val y = waveHeight * sin((x / waveLength) * 2 * Math.PI.toFloat() - waveOffset)
                        lineTo(x.toFloat(), y.toFloat())
                    }
                    lineTo(size.width, waveHeight)
                    lineTo(size.width, size.height / 2)
                    lineTo(0f, size.height / 2)
                    close()
                }
                drawPath(path, Color.Blue.copy(alpha = 0.1f)) // Set alpha to 0.3 for translucency
            }
        }
    }
}

@Preview
@Composable
private fun WaveAnimationScreenPreiew() {
    WaveAnimationScreen()
}