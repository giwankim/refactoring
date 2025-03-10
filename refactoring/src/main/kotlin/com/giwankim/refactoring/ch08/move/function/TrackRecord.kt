package com.giwankim.refactoring.ch08.move.function

import java.time.Duration
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Calculates the total distance for a GPS track record.
 *
 * This function processes a list of GPS points and computes a summary containing:
 * - total distance traveled
 * - total time elapsed
 * - average pace
 *
 * @param points List of GPS coordinates representing the track
 * @return A summary containing distance, time, and pace information
 */
fun trackSummary(points: List<Point>): TrackSummary {
    fun calculateTime(): Duration {
        if (points.size < 2) {
            return Duration.ZERO
        }
        val start = points.first().timestamp
        val end = points.last().timestamp
        return Duration.between(start, end)
    }

    fun calculateDistance(): Double {
        fun radians(degrees: Double): Double = degrees * Math.PI / 180

        fun distance(
            p1: Point,
            p2: Point,
        ): Double {
            // haversine formula see http://www.movable-type.co.uk/scripts/latlong.html
            val earthRadius = 3959 // in miles
            val dLat = radians(p2.lat) - radians(p1.lat)
            val dLon = radians(p2.lon) - radians(p1.lon)
            val a =
                sin(dLat / 2).pow(2) +
                    cos(radians(p1.lat)) * cos(radians(p2.lat)) *
                    sin(dLon / 2) * sin(dLon / 2)
            val c = 2 * atan2(sqrt(a), sqrt(1 - a))
            return earthRadius * c
        }

        return topCalculateDistance(points)
    }

    val totalTime = calculateTime()
    val totalDistance = calculateDistance()
    val pace = totalTime.toMinutes() / 60.0 / totalDistance
    return TrackSummary(totalTime, totalDistance, pace)
}

fun topCalculateDistance(points: List<Point>): Double {
    fun radians(degrees: Double): Double = degrees * Math.PI / 180

    fun distance(
        p1: Point,
        p2: Point,
    ): Double {
        // haversine formula see http://www.movable-type.co.uk/scripts/latlong.html
        val earthRadius = 3959 // in miles
        val dLat = radians(p2.lat) - radians(p1.lat)
        val dLon = radians(p2.lon) - radians(p1.lon)
        val a =
            sin(dLat / 2).pow(2) +
                cos(radians(p1.lat)) * cos(radians(p2.lat)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }

    return points
        .zipWithNext { p1, p2 -> distance(p1, p2) }
        .sum()
}
