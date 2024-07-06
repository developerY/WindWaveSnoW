package com.ylabz.windwatersnow.wind.ui.components.hold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RainCard()
            }
        }
    }
}

@Composable
fun RainCard() {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFBBDEFB))
            .padding(16.dp)
    ) {
         Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "Current Rain Conditions",
                style = MaterialTheme.typography.h5,
                color = Color(0xFF009688)
            )
            Spacer(modifier = Modifier.height(16.dp))
            RainAnimation()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Light Rain",
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "18°C",
                fontSize = 24.sp,
                color = Color(0xFF009688)
            )
        }
    }
}

@Composable
fun RainAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val rainDropOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.size(100.dp)) {
        val raindropColor = Color(0xFF009688)
        for (i in 1..5) {
            drawLine(
                color = raindropColor,
                start = center.copy(y = (center.y - 50f + rainDropOffset) % size.height),
                end = center.copy(y = (center.y + rainDropOffset) % size.height),
                strokeWidth = 4.dp.toPx()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RainCardPreview() {
    MaterialTheme {
        RainCard()
    }
}
