package com.ylabz.windwatersnow.wind.ui.components.cards.Wind

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WindAnimation(windDegree: Float) {
    val screenWidth = 500f
    val screenHeight = 500f

    val windArrows = remember {
        List(10) {
            WindArrow(
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                sizeRange = 10f..30f,
                speedRange = 2f..5f,
                degree = windDegree
            )
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        windArrows.forEach { arrow ->
            arrow.move()
            drawArrow(
                arrow.x,
                arrow.y,
                arrow.size,
                arrow.getRotation()
            )
        }
    }
}

fun DrawScope.drawArrow(x: Float, y: Float, size: Float, rotation: Float) {
    drawLine(
        color = Color.Blue,
        start = Offset(x, y),
        end = Offset(x + size * cos(rotation), y + size * sin(rotation)),
        strokeWidth = 4f
    )
}
