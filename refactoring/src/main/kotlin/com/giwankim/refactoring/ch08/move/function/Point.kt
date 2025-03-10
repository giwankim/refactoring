package com.giwankim.refactoring.ch08.move.function

import java.time.Instant

data class Point(
    val lat: Double,
    val lon: Double,
    val timestamp: Instant,
)
