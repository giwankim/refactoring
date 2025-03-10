package com.giwankim.refactoring.ch08.move.function

import java.time.Duration

data class TrackSummary(
    val time: Duration,
    val distance: Double,
    val pace: Double,
)
