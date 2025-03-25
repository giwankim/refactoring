package com.giwankim.refactoring.ch10.decompose.conditional

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class ChargeCalculatorTest :
    FunSpec({
        context("calculateCharge") {
            test("summer") {
                val summerPlan =
                    Plan(
                        summerStart = LocalDate.of(2021, 6, 1),
                        summerEnd = LocalDate.of(2021, 8, 31),
                        summerRate = 10.0,
                        regularRate = 20.0,
                        regularServiceCharge = 5.0,
                    )
                calculateCharge(summerPlan, LocalDate.of(2021, 7, 1), 10) shouldBe 100.0
            }

            test("regular") {
                val regularPlan =
                    Plan(
                        summerStart = LocalDate.of(2021, 6, 1),
                        summerEnd = LocalDate.of(2021, 8, 31),
                        summerRate = 10.0,
                        regularRate = 20.0,
                        regularServiceCharge = 5.0,
                    )
                calculateCharge(regularPlan, LocalDate.of(2021, 9, 1), 10) shouldBe 205.0
            }
        }
    })
