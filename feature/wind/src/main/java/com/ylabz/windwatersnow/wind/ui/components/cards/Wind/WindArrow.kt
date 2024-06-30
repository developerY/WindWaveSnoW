package com.ylabz.windwatersnow.wind.ui.components.cards.Wind

import kotlin.random.Random

class WindArrow(
    private val screenWidth: Float,
    private val screenHeight: Float,
    private val sizeRange: ClosedFloatingPointRange<Float>,
    private val speedRange: ClosedFloatingPointRange<Float>,
    private val degree: Float  // Add a degree parameter
) {
    private val random = Random(System.nanoTime())
    val size: Float = random.nextFloat() * (sizeRange.endInclusive - sizeRange.start) + sizeRange.start
    var speed: Float = random.nextFloat() * (speedRange.endInclusive - speedRange.start) + speedRange.start //speedRange.randomInRange()
    var x: Float = random.nextFloat() * screenWidth
    var y: Float = random.nextFloat() * screenHeight
    var phase: Float = 0f

    fun move() {
        x += speed
        phase += 0.1f  // Increment phase for squiggly effect
        if (x > screenWidth) {
            x = -size * 2
            y = random.nextFloat() * screenHeight
            phase = 0f  // Reset phase when repositioning
        }
    }

    fun getRotation(): Float {
        return degree
    }
}
