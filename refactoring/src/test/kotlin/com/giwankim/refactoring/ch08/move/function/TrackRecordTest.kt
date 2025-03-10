package com.giwankim.refactoring.ch08.move.function

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import java.time.Duration
import java.time.Instant

class TrackRecordTest :
    FunSpec({
        test("trackSummary") {
            val startTime = Instant.parse("2024-03-10T10:00:00Z")
            val points =
                listOf(
                    Point(37.7749, -122.4194, startTime), // San Francisco (start)
                    Point(37.8044, -122.2712, startTime.plusSeconds(600)), // Oakland after 10 minutes
                    Point(37.8715, -122.2730, startTime.plusSeconds(1200)), // Berkeley after 20 minutes
                )

            val summary = trackSummary(points)

            assertSoftly {
                summary.time shouldBe Duration.ofMinutes(20)
                summary.distance shouldBe (12.982785244590303 plusOrMinus 1e-6)
                summary.pace shouldBe (0.025675024815821196 plusOrMinus 1e-6)
            }
        }
    })
