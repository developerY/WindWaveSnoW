package com.ylabz.windwatersnow.wind.ui.components.cards.Wind

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WindDirectionDial(degree: Float) {
    val infiniteTransition = rememberInfiniteTransition()
    val wiggle by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "wiggle"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = size.minDimension / 2
            val center = Offset(size.width / 2, size.height / 2)
            val wiggleDegree = degree + wiggle


            // Draw the dial circle
            drawCircle(
                color = Color.LightGray,
                radius = radius,
                center = center
            )

            // Draw the arrow shape
            val arrowLength = radius * 0.8f
            val arrowWidth = radius * 0.1f
            val arrowAngleRad = Math.toRadians(wiggleDegree.toDouble() - 90)  // Rotate to point north
            val arrowEnd = Offset(
                center.x + (arrowLength * cos(arrowAngleRad)).toFloat(),
                center.y + (arrowLength * sin(arrowAngleRad)).toFloat()
            )

            // Arrow Path
            val arrowPath = Path().apply {
                moveTo(center.x, center.y)
                lineTo(
                    center.x + (arrowWidth * cos(arrowAngleRad + Math.PI / 2)).toFloat(),
                    center.y + (arrowWidth * sin(arrowAngleRad + Math.PI / 2)).toFloat()
                )
                lineTo(arrowEnd.x, arrowEnd.y)
                lineTo(
                    center.x + (arrowWidth * cos(arrowAngleRad - Math.PI / 2)).toFloat(),
                    center.y + (arrowWidth * sin(arrowAngleRad - Math.PI / 2)).toFloat()
                )
                close()
            }

            drawPath(path = arrowPath, color = Color.Red)

            // Draw degree markers (optional)
            for (i in 0..360 step 30) {
                val markerAngleRad = Math.toRadians(i.toDouble() - 90)
                val markerStart = Offset(
                    center.x + (radius * 0.9 * cos(markerAngleRad)).toFloat(),
                    center.y + (radius * 0.9 * sin(markerAngleRad)).toFloat()
                )
                val markerEnd = Offset(
                    center.x + (radius * cos(markerAngleRad)).toFloat(),
                    center.y + (radius * sin(markerAngleRad)).toFloat()
                )
                drawLine(
                    color = Color.Black,
                    start = markerStart,
                    end = markerEnd,
                    strokeWidth = 4f
                )
            }
        }
        // Draw the degree number over the dial
        Text(
            text = "${degree.toInt()}°",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
        )
    }
}
