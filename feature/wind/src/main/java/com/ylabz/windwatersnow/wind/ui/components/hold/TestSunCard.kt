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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SunWeatherCard(
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
        SunAnimation()
    }
}

@Composable
fun SunAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 6000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        drawSun(rotation.value, this)
    }
}

fun DrawScope.drawSun(rotation: Float, scope: DrawScope) {
    val sunCenter = Offset(size.width / 2, size.height / 2)
    val sunRadius = 50f
    val rayLength = 20f


    scope.drawCircle(
        color = Color.Yellow,
        center = sunCenter,
        radius = sunRadius
    )

    scope.rotate(rotation, sunCenter) {
        for (i in 0 until 12) {
            val angle = i * 30f
            val rayStart = Offset(
                x = sunCenter.x + sunRadius * kotlin.math.cos(Math.toRadians(angle.toDouble())).toFloat(),
                y = sunCenter.y + sunRadius * kotlin.math.sin(Math.toRadians(angle.toDouble())).toFloat()
            )
            val rayEnd = Offset(
                x = sunCenter.x + (sunRadius + rayLength) * kotlin.math.cos(Math.toRadians(angle.toDouble())).toFloat(),
                y = sunCenter.y + (sunRadius + rayLength) * kotlin.math.sin(Math.toRadians(angle.toDouble())).toFloat()
            )
            scope.drawLine(
                color = Color.Yellow,
                start = rayStart,
                end = rayEnd,
                strokeWidth = 5f
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SunCardPreview() {
    MaterialTheme {
        SunWeatherCard(
            temperature = "30°C",
            weatherCondition = "Sunny",
            description = "Clear sky"
        )
    }
}
