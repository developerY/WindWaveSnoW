package com.ylabz.windwatersnow.wind.ui.components.cards.snow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SnowVolumeCardAI(volume: Double) {
    val cardHeight = 200.dp
    val initialAccumulatedSnow = 20f // Initial accumulated snow height
    val accumulatedSnowHeight = remember { mutableStateOf(initialAccumulatedSnow) }
    val snowflakes = remember { List(500) { Snowflake(1200f, cardHeight.value, 1f..2.5f, 1f..5f) } }

    LaunchedEffect(Unit) {
        while (true) {
            snowflakes.forEach { snowflake ->
                snowflake.move()
                if (snowflake.y > cardHeight.value * 3) {
                    accumulatedSnowHeight.value += snowflake.size / 10f
                    snowflake.resetPosition(cardHeight.value)
                }
            }
            delay(16)  // Roughly 60 FPS
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF9C27B0))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Draw accumulated snow
                drawRect(
                    color = Color.White,
                    topLeft = Offset(0f, size.height - accumulatedSnowHeight.value),
                    size = Size(size.width, accumulatedSnowHeight.value)
                )
                // Draw falling snowflakes
                snowflakes.forEach { snowflake ->
                    drawCircle(
                        color = Color.White,
                        radius = snowflake.size,
                        center = Offset(snowflake.x, snowflake.y)
                    )
                }
            }
            // Display snow volume text
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
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

data class Snowflake(
    val screenWidth: Float,
    val screenHeight: Float,
    val sizeRange: ClosedFloatingPointRange<Float>,
    val speedRange: ClosedFloatingPointRange<Float>
) {
    private val random = Random(System.nanoTime())
    val size: Float = sizeRange.randomInRange()
    var speed: Float = speedRange.randomInRange()
    var x: Float = random.nextFloat() * screenWidth
    var y: Float = random.nextFloat() * screenHeight

    fun move() {
        y += speed
    }

    fun resetPosition(height: Float) {
        y =  -size
        x = random.nextFloat() * screenWidth
        speed = speedRange.randomInRange()
    }
}

fun ClosedFloatingPointRange<Float>.randomInRange(): Float {
    return Random.nextFloat() * (endInclusive - start) + start
}

@Preview(showBackground = true)
@Composable
fun SnowVolumeCardPreview() {
    SnowVolumeCardAI(volume = 10.0)
}
