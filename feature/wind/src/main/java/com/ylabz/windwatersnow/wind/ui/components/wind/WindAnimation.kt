package com.ylabz.windwatersnow.wind.ui.components.wind

import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun WindAnimation(
    numArrows: Int = 20,
    modifier: Modifier = Modifier.fillMaxSize(),
    sizeRange: ClosedFloatingPointRange<Float> = 16f..32f,
    speedRange: ClosedFloatingPointRange<Float> = 2f..6f
) {
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val arrows = remember {
        List(numArrows) {
            WindArrow(screenWidth, screenHeight, sizeRange, speedRange)
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

    // Use LaunchedEffect to create an infinite loop that updates the arrows' positions
    LaunchedEffect(animation) {
        while (true) {
            withFrameNanos {
                arrows.forEach { it.move() }
            }
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        arrows.forEach { arrow ->
            for (i in 0..10) {
                val startX = arrow.x + i * arrow.size
                val endX = startX + arrow.size
                val offsetY = sin(arrow.phase + i * 0.5f) * 10f  // Sine wave for squiggly effect

                drawLine(
                    color = Color.Gray.copy(alpha = 0.5f),
                    strokeWidth = arrow.size / 4,
                    start = Offset(startX, arrow.y + offsetY),
                    end = Offset(endX, arrow.y + offsetY)
                )
            }
        }
    }
}
class WindArrow(
    private val screenWidth: Float,
    private val screenHeight: Float,
    private val sizeRange: ClosedFloatingPointRange<Float>,
    private val speedRange: ClosedFloatingPointRange<Float>
) {
    private val random = Random(System.nanoTime())
    val size: Float = sizeRange.randomInRange()
    var speed: Float = speedRange.randomInRange()
    var x: Float = random.nextFloat() * screenWidth
    var y: Float = random.nextFloat() * screenHeight
    var phase: Float = 0f

    fun move() {
        x += speed
        phase += 0.1f  // Increment phase for squiggly effect
        if (x > screenWidth) {
            x = -size * 2
            y = random.nextFloat() * screenHeight
            phase = 0f  // Reset phase when repositioning
        }
    }
}

private fun ClosedFloatingPointRange<Float>.randomInRange(): Float {
    return Random.nextFloat() * (endInclusive - start) + start
}

@Preview
@Composable
private fun WindAnimationPreview() {
    WindAnimation()
}
