package com.ylabz.windwatersnow.wind.ui.components.hold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SnowWeatherCard(
    temperature: String,
    weatherCondition: String,
    description: String
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = weatherCondition,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = temperature,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        SnowAnimation()
    }
}

@Composable
fun SnowAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val flakePositions = List(50) {
        infiniteTransition.animateFloat(
            initialValue = -100f,
            targetValue = 800f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 4000,
                    delayMillis = (it * 50),
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        flakePositions.forEach { flakePosition ->
            drawSnowFlake(flakePosition.value, this)
        }
    }
}

fun DrawScope.drawSnowFlake(y: Float, scope: DrawScope) {
    scope.drawCircle(
        color = Color.White,
        center = Offset(x = (0..scope.size.width.toInt()).random().toFloat(), y = y),
        radius = 5f
    )
}

@Preview(showBackground = true)
@Composable
fun SnowCardPreview() {
    MaterialTheme {
        SnowWeatherCard(
            temperature = "-5°C",
            weatherCondition = "Snowy",
            description = "Heavy snow"
        )
    }
}
