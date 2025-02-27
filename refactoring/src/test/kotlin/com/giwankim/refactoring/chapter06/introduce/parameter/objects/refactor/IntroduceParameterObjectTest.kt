package com.giwankim.refactoring.chapter06.introduce.parameter.objects.refactor

import com.giwankim.refactoring.chapter06.introduce.parameter.objects.Reading
import com.giwankim.refactoring.chapter06.introduce.parameter.objects.Station
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import java.time.LocalDateTime

class IntroduceParameterObjectTest :
    FunSpec({
        test("readings outside range") {
            val station =
                Station(
                    "ZB1",
                    listOf(
                        Reading(47, LocalDateTime.of(2016, 11, 10, 9, 10)),
                        Reading(53, LocalDateTime.of(2016, 11, 10, 9, 20)),
                        Reading(58, LocalDateTime.of(2016, 11, 10, 9, 30)),
                        Reading(53, LocalDateTime.of(2016, 11, 10, 9, 40)),
                        Reading(51, LocalDateTime.of(2016, 11, 10, 9, 50)),
                    ),
                )
            val range = NumberRange(50, 55)

            val readings = readingsOutsideRange(station, 50, range)

            readings.map(Reading::temp) shouldContainExactlyInAnyOrder listOf(51, 53, 53)
        }
    })
