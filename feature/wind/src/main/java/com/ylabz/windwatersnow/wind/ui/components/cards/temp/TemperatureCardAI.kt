package com.ylabz.windwatersnow.wind.ui.components.cards.temp

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToLong


@Composable
fun TemperatureCardAI(temp: Double) {
    // State to toggle between Celsius and Fahrenheit
    var isCelsius by remember { mutableStateOf(true) }
    val temperature = if (isCelsius) temp else temp * 9 / 5 + 32
    val unit = if (isCelsius) "°C" else "°F"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .clickable {
                isCelsius = !isCelsius
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC107))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Sunshine animation positioned at the top left
            Sunshine(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                rotationAngle = rememberInfiniteTransition().animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 12000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ), label = "rotAng"
                ).value,
                scaleFactor = rememberInfiniteTransition().animateFloat(
                    initialValue = 1f,
                    targetValue = 1.5f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 2500, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    ), label = "scaleFac"
                ).value,
                breakoutDistance = rememberInfiniteTransition().animateFloat(
                    initialValue = 0f,
                    targetValue = 1000f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 5000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ), label = "breakoutDis"
                ).value,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Temperature",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${temperature.roundToLong()} $unit",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Thermometer(temperature = temperature.toFloat())
            }
        }
    }
}
