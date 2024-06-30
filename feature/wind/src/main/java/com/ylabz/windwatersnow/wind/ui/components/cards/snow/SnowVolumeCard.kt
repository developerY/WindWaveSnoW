package com.ylabz.windwatersnow.wind.ui.components.cards.snow

import SnowVolumeCard
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

import androidx.compose.runtime.remember

import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import kotlinx.coroutines.delay

import kotlin.math.cos
import kotlin.math.sin


@Composable
fun SnowVolumeCardAI(volume: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF9C27B0))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Snow Volume",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$volume mm",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            SnowfallAnimation(volume)
        }
    }
}

@Composable
fun SnowfallAnimation(volume: Double) {
    val snowflakes = remember { mutableStateListOf<Snowflake>() }
    val snowflakeCount = (volume * 10).toInt().coerceAtLeast(10) // Ensuring at least 10 snowflakes
    val accumulatedSnow = remember { mutableStateOf(0f) }

    LaunchedEffect(snowflakeCount) {
        snowflakes.clear()
        repeat(snowflakeCount) {
            snowflakes.add(Snowflake())
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)) {
        val width = size.width
        val height = size.height

        snowflakes.forEach { snowflake ->
            val currentY = (snowflake.y + offsetY * height) % height
            if (currentY >= height - accumulatedSnow.value) {
                accumulatedSnow.value += snowflake.size / 20 // Accumulate snow at the bottom
                snowflake.resetPosition(height)
            } else {
                drawCircle(
                    color = Color.White,
                    radius = snowflake.size,
                    center = Offset(
                        x = snowflake.x,
                        y = currentY
                    )
                )
            }
        }

        // Draw accumulated snow
        drawRect(
            color = Color.White,
            topLeft = Offset(0f, height - accumulatedSnow.value),
            size = androidx.compose.ui.geometry.Size(width, accumulatedSnow.value)
        )
    }
}

class Snowflake {
    val size = (2..6).random().toFloat()
    val x = (0..1000).random().toFloat()
    var y = (0..1000).random().toFloat()

    fun resetPosition(height: Float) {
        y = (0..(height / 2).toInt()).random().toFloat()
    }
}

@Preview(showBackground = true)
@Composable
fun SnowVolumeCardPreview() {
    SnowVolumeCard(volume = 10.0)
}
