package com.giwankim.refactoring.ch10.replace.conditional.with.polymorphism.variation

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RatingAssessorTest :
    FunSpec({
        context("rating") {
            test("A rating") {
                val voyage = Voyage(zone = "china", length = 10, profit = 0)
                val history =
                    History(
                        listOf(
                            Voyage(zone = "east-indies", length = 1, profit = 5),
                            Voyage(zone = "west-indies", length = 1, profit = 15),
                            Voyage(zone = "china", length = 1, profit = -2),
                            Voyage(zone = "west-africa", length = 1, profit = 7),
                        ),
                    )
                rating(voyage, history) shouldBe "A"
            }

            test("B rating") {
                val voyage = Voyage(zone = "china", length = 10, profit = 0)
                val history =
                    History(
                        listOf(
                            Voyage(zone = "greenland", length = 30, profit = -10),
                            Voyage(zone = "antarctica", length = 50, profit = -20),
                            Voyage(zone = "uk", length = 10, profit = 1),
                            Voyage(zone = "us", length = 20, profit = 0),
                        ),
                    )
                rating(voyage, history) shouldBe "B"
            }
        }
    })
