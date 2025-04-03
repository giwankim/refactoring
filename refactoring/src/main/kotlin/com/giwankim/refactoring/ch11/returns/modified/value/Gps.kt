package com.giwankim.refactoring.ch11.returns.modified.value

fun main() {
    val points = listOf(Point(100), Point(200), Point(150))

    var totalTime = 0
    var totalDistance = 0

    fun calculateAscent(): Int {
        var result = 0
        for (i in 1 until points.size) {
            val verticalChange = points[i].elevation - points[i - 1].elevation
            result += verticalChange.coerceAtLeast(0)
        }
        return result
    }

    fun calculateTime() {
        // Assuming each point has a time property
    }

    fun calculateDistance() {
        // Assuming each point has a distance property
    }

    val totalAscent = calculateAscent()
    calculateTime()
    calculateDistance()
    val pace = totalTime / 60.0 / totalDistance
}
