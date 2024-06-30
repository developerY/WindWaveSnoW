package com.ylabz.windwatersnow.wind.ui.components.cards.Waves

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import kotlinx.coroutines.delay

import kotlin.math.cos
import kotlin.math.sin


@Composable
fun WaveGraphic(height: Double) {
    val waveColor = Color.White
    val waveFrequency = 2f   // Frequency of the wave
    val waveSpeed = 0.05f    // Speed of the wave

    // Animatable for wave amplitude
    val waveAmplitude = remember { Animatable(initialValue = 50f) }
    LaunchedEffect(Unit) {
        while (true) {
            val newAmplitude = (30..100).random().toFloat() // Random wave amplitude between 30 and 100
            waveAmplitude.animateTo(
                targetValue = newAmplitude,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
            )
            delay(2000) // Change wave amplitude every 2 seconds
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        val wavePath = Path()
        val width = size.width
        val heightCanvas = size.height

        wavePath.moveTo(0f, heightCanvas / 2)

        for (x in 0 until width.toInt()) {
            val y = (heightCanvas / 2) + waveAmplitude.value * sin((x / width) * waveFrequency * 2 * Math.PI.toFloat() - phase).toFloat()
            wavePath.lineTo(x.toFloat(), y.toFloat())
        }

        wavePath.lineTo(width, heightCanvas)
        wavePath.lineTo(0f, heightCanvas)
        wavePath.close()

        drawPath(
            path = wavePath,
            color = waveColor,
            style = Fill
        )
    }
}