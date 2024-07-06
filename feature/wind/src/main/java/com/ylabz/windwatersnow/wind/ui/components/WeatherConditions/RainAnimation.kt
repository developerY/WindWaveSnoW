package com.ylabz.windwatersnow.wind.ui.components.WeatherConditions

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun RainAnimationScreen() {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    // Convert screen dimensions from dp to pixels
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            //.border(2.dp, Color.Red) // Add a red border with 2 dp thickness
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "")

        // List of offsets for raindrops
        val raindrops = remember {
            List(250) {
                Raindrop(
                    x = Random.nextFloat() * screenWidthPx,
                    startY = -Random.nextFloat() * screenHeightPx,
                    endY = screenHeightPx * 2,
                    length = Random.nextFloat() * 90 + 10,
                    width = Random.nextFloat() * 2 + 1,
                    duration = Random.nextInt(3000, 5000)
                )
            }
        }

        val animations = raindrops.map { raindrop ->
            infiniteTransition.animateFloat(
                initialValue = raindrop.startY,
                targetValue = raindrop.endY,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = raindrop.duration,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ), label = ""
            )
        }

        Rain(raindrops, animations)
    }
}

@Composable
fun Rain(raindrops: List<Raindrop>, animations: List<State<Float>>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        for (i in raindrops.indices) {
            val raindrop = raindrops[i]
            val yOffset = animations[i].value

            drawLine(
                color = Color.Blue.copy(alpha = 0.5f),
                start = Offset(raindrop.x, yOffset),
                end = Offset(raindrop.x, yOffset + raindrop.length),
                strokeWidth = raindrop.width
            )
        }
    }
}

data class Raindrop(
    val x: Float,
    val startY: Float,
    val endY: Float,
    val length: Float,
    val width: Float,
    val duration: Int
)

@Preview
@Composable
private fun RainAnimationScreenPreview() {
    RainAnimationScreen()
}