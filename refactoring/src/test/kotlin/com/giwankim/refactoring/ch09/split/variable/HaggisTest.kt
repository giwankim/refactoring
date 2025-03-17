package com.giwankim.refactoring.ch09.split.variable

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class HaggisTest :
    FunSpec({
        test("distanceTravelled") {
            val scenario = Scenario(primaryForce = 10.0, mass = 2.0, delay = 5, secondaryForce = 5.0)
            distanceTravelled(scenario, 10) shouldBe 281.25 plusOrMinus 1e-6
        }
    })
