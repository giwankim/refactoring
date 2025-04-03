package com.giwankim.refactoring.ch11.returns.modified.value

fun main() {
    val points = listOf(Point(100), Point(200), Point(150))

    var totalDistance = 0

    fun calculateAscent(): Int {
        var result = 0
        for (i in 1 until points.size) {
            val verticalChange = points[i].elevation - points[i - 1].elevation
            result += verticalChange.coerceAtLeast(0)
        }
        return result
    }

    fun calculateTime(): Int {
        var totalTime = 0
        // Assuming each point has a time property
        return totalTime
    }

    fun calculateDistance() {
        // Assuming each point has a distance property
    }

    val totalAscent = calculateAscent()
    val totalTime = calculateTime()
    calculateDistance()
    val pace = totalTime / 60.0 / totalDistance
}
