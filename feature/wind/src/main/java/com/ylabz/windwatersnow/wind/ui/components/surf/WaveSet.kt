package com.ylabz.windwatersnow.wind.ui.components.surf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.sin

@Composable
fun WaveAnimationScreenSet() {
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Animatable for wave height
    val waveHeight = remember { Animatable(initialValue = 50f) }
    LaunchedEffect(Unit) {
        while (true) {
            val newHeight = (30..500).random().toFloat() // Random wave height between 30 and 100
            waveHeight.animateTo(
                targetValue = newHeight,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
            delay(2000) // Change wave height every 2 seconds
        }
    }

    WaveSet(modifier = Modifier.fillMaxSize(), waveOffset = waveOffset, waveHeight = waveHeight.value)
}

@Composable
fun WaveSet(modifier: Modifier = Modifier, waveOffset: Float, waveHeight: Float) {
    Canvas(modifier = modifier) {
        val waveLength = size.width / 1.5f

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

@Preview
@Composable
private fun WaveAnimationScreenSetPreview() {
    WaveAnimationScreenSet()
}
