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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Thermometer(temperature: Float) {
    val maxTemp = 100f  // Maximum temperature for the thermometer scale
    val minTemp = -10f // Minimum temperature for the thermometer scale
    val thermometerWidth = 300.dp
    val thermometerHeight = 40.dp

    Box(
        modifier = Modifier
            .width(thermometerWidth)
            .height(thermometerHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        // Thermometer tube
        Canvas(modifier = Modifier.fillMaxSize()) {
            val tubeHeight = size.height / 2
            drawRoundRect(
                color = Color.White,
                topLeft = Offset(x = 0f, y = size.height / 4),
                size = Size(width = size.width, height = tubeHeight),
                cornerRadius = CornerRadius(x = tubeHeight / 2, y = tubeHeight / 2)
            )
        }

        // Mercury level
        val mercuryWidth = thermometerWidth * ((temperature - minTemp) / (maxTemp - minTemp))
        Box(
            modifier = Modifier
                .height(thermometerHeight / 2)
                .width(mercuryWidth)
                .background(Color.Red)
                .align(Alignment.CenterStart)
        )

        // Mercury bulb (hidden for horizontal layout)
        //Box(
        //modifier = Modifier
        //         .size(thermometerHeight / 2)
        //         .background(Color.Red, shape = CircleShape)
        //         .align(Alignment.CenterStart)
        // )
            // Markers
            val numberOfMarkers = 6
            val markerInterval = (maxTemp - minTemp) / numberOfMarkers
            for (i in 0..numberOfMarkers) {
                val markerTemp = minTemp + (i * markerInterval)
                val markerPosition =
                    thermometerWidth * ((markerTemp - minTemp) / (maxTemp - minTemp))

                Box(
                    modifier = Modifier
                        .offset(x = markerPosition)
                        .width(1.dp)
                        .height(thermometerHeight)
                        .background(Color.Black)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = markerTemp.toInt().toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.offset(x = markerPosition, y = (-20).dp)
                )
            }

    }
}