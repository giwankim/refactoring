package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BirdsTest :
    FunSpec({
        test("plumages") {
            val birds =
                listOf(
                    Bird(
                        name = "bird1",
                        type = "EuropeanSwallow",
                        numberOfCoconuts = 1,
                        voltage = 2.0,
                        isNailed = false,
                    ),
                    Bird(
                        name = "bird2",
                        type = "AfricanSwallow",
                        numberOfCoconuts = 0,
                        voltage = 2.0,
                        isNailed = false,
                    ),
                    Bird(
                        name = "bird3",
                        type = "AfricanSwallow",
                        numberOfCoconuts = 3,
                        voltage = 2.0,
                        isNailed = false,
                    ),
                    Bird(
                        name = "bird4",
                        type = "NorwegianBlueParrot",
                        numberOfCoconuts = 0,
                        voltage = 100.0,
                        isNailed = false,
                    ),
                    Bird(
                        name = "bird5",
                        type = "NorwegianBlueParrot",
                        numberOfCoconuts = 0,
                        voltage = 101.0,
                        isNailed = false,
                    ),
                )
            plumages(birds) shouldBe
                mapOf(
                    "bird1" to Plumage.AVERAGE,
                    "bird2" to Plumage.AVERAGE,
                    "bird3" to Plumage.TIRED,
                    "bird4" to Plumage.BEAUTIFUL,
                    "bird5" to Plumage.SCORCHED,
                )
        }

        test("speeds") {
            val birds =
                listOf(
                    Bird(
                        name = "bird1",
                        type = "EuropeanSwallow",
                        numberOfCoconuts = 0,
                        voltage = 0.0,
                        isNailed = false,
                    ),
                    Bird(
                        name = "bird2",
                        type = "AfricanSwallow",
                        numberOfCoconuts = 3,
                        voltage = 0.0,
                        isNailed = false,
                    ),
                    Bird(
                        name = "bird3",
                        type = "NorwegianBlueParrot",
                        numberOfCoconuts = 0,
                        voltage = 0.0,
                        isNailed = true,
                    ),
                    Bird(
                        name = "bird4",
                        type = "NorwegianBlueParrot",
                        numberOfCoconuts = 0,
                        voltage = 100.0,
                        isNailed = false,
                    ),
                )
            speeds(birds) shouldBe mapOf("bird1" to 35.0, "bird2" to 34.0, "bird3" to 0.0, "bird4" to 20.0)
        }
    })
