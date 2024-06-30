package com.ylabz.windwatersnow.wind.ui.components.cards.temp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Thermometer(temperature: Float) {
    val maxTemp = 50f  // Maximum temperature for the thermometer scale
    val minTemp = -10f // Minimum temperature for the thermometer scale
    val thermometerHeight = 200.dp
    val thermometerWidth = 40.dp

    Box(
        modifier = Modifier
            .height(thermometerHeight)
            .width(thermometerWidth),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Thermometer tube
        Canvas(modifier = Modifier.fillMaxSize()) {
            val tubeWidth = size.width / 2
            drawRoundRect(
                color = Color.White,
                topLeft = Offset(x = size.width / 4, y = 0f),
                size = Size(width = tubeWidth, height = size.height),
                cornerRadius = CornerRadius(x = tubeWidth / 2, y = tubeWidth / 2)
            )
            drawCircle(
                color = Color.White,
                center = Offset(x = size.width / 2, y = size.height),
                radius = tubeWidth / 2
            )
        }

        // Mercury level
        val mercuryHeight = thermometerHeight * ((temperature - minTemp) / (maxTemp - minTemp))
        Box(
            modifier = Modifier
                .width(thermometerWidth / 2)
                .height(mercuryHeight)
                .background(Color.Red)
                .align(Alignment.BottomCenter)
        )

        // Mercury bulb
        Box(
            modifier = Modifier
                .size(thermometerWidth / 2)
                .background(Color.Red, shape = CircleShape)
                .align(Alignment.BottomCenter)
        )
    }
}
