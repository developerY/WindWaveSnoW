package com.ylabz.windwatersnow.core.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun PulsatingIcon(iconToUse : ImageVector) {

    val infiniteTransition = rememberInfiniteTransition()
    val radius by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 50f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(3000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    //SquareToCircleAnimation()
    Icon(
        imageVector = iconToUse,
        contentDescription = "Add",
        modifier = Modifier.size(radius.dp * 2f)
    )
}

@Composable
fun PulsatingColorIcon(iconToUse: ImageVector) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val radius by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val animationDuration = 7000 // in milliseconds
    val animationColors = listOf(Color.Green, Color(0xFF006400))

    val color by infiniteTransition.animateColor(
        initialValue = animationColors.first(),
        targetValue = animationColors.last(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )

    Icon(
        imageVector = iconToUse,
        contentDescription = "Add",
        tint = color,
        modifier = Modifier.size(100.dp)
        //size(radius.dp * 2f)
    )
}


@Composable
fun SquareToCircleAnimation() {
    val transition = rememberInfiniteTransition(label = "")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            tween(1500),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        val radius = size.width * progress / 2f
        val center = size.center

        drawCircle(
            color = Color.Blue,
            radius = radius,
            center = center
        )

        if (progress < 1f) {
            drawRect(
                color = Color.Blue.copy(alpha = 0.5f),
                size = Size(size.width, size.width),
                //center = center
            )
        }
    }
}
