package com.ylabz.windwatersnow.wind.ui.components.snow

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class Snowflake(
    private val screenWidth: Float,
    private val screenHeight: Float,
    private val radiusRange: ClosedFloatingPointRange<Float>,
    private val speedRange: ClosedFloatingPointRange<Float>
) {
    private val random = Random(System.nanoTime())
    val radius: Float = radiusRange.randomInRange()
    var speed: Float = speedRange.randomInRange()
    var x: Float = random.nextFloat() * screenWidth
    var y: Float = random.nextFloat() * screenHeight

    fun fall() {
        y += speed
        if (y > screenHeight) {
            y = -radius
            x = random.nextFloat() * screenWidth
        }
    }
}

private fun ClosedFloatingPointRange<Float>.randomInRange(): Float {
    return Random.nextFloat() * (endInclusive - start) + start
}

@Composable
fun SnowfallAnimation(
    numFlakes: Int = 100,
    modifier: Modifier = Modifier,
    radiusRange: ClosedFloatingPointRange<Float> = 4f..12f,
    speedRange: ClosedFloatingPointRange<Float> = 4f..20f
) {
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val snowflakes = remember {
        List(numFlakes) {
            Snowflake(screenWidth, screenHeight, radiusRange, speedRange)
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val animation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 16, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Use LaunchedEffect to create an infinite loop that updates the snowflakes' positions
    LaunchedEffect(animation) {
        snowflakes.forEach { it.fall() }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        snowflakes.forEach { flake ->
            flake.fall()
            drawCircle(
                color = Color.White.copy(alpha = 0.7f),
                radius = flake.radius,
                center = Offset(flake.x, flake.y)
            )
        }
    }
}

