package com.ylabz.windwatersnow.wind.ui.components.hold

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun WindWeatherCard(
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
        WindAnimation()
    }
}

@Composable
fun WindAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val windPositions = List(10) {
        infiniteTransition.animateFloat(
            initialValue = -100f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 3000,
                    delayMillis = (it * 300),
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        windPositions.forEach { windPosition ->
            drawWindGust(windPosition.value, this)
        }
    }
}

fun DrawScope.drawWindGust(x: Float, scope: DrawScope) {
    val y = (0..scope.size.height.toInt()).random().toFloat()
    scope.drawLine(
        color = Color.White,
        start = Offset(x = x, y = y),
        end = Offset(x = x + 50, y = y),
        strokeWidth = 3f
    )
}

@Preview(showBackground = true)
@Composable
fun WindCardPreview() {
    MaterialTheme {
        WindWeatherCard(
            temperature = "15°C",
            weatherCondition = "Windy",
            description = "Strong winds"
        )
    }
}
