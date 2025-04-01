package com.giwankim.refactoring.ch11.preserve.whole.objects

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HeatingPlanTest :
    FunSpec({
        test("within range returns true when temperature range is completely within bounds") {
            val heatingPlan = HeatingPlan(Range(low = 70, high = 80))
            val range = Range(low = 65, high = 85)
            heatingPlan.xxNEWwithinRange(range) shouldBe true
        }

        test("within range returns true when temperature range is exactly the same as bounds") {
            val heatingPlan = HeatingPlan(Range(low = 65, high = 85))
            val range = Range(low = 65, high = 85)
            heatingPlan.xxNEWwithinRange(range) shouldBe true
        }

        test("within range returns false when low temperature is below the bottom bound") {
            val heatingPlan = HeatingPlan(Range(low = 60, high = 80))
            val range = Range(low = 65, high = 85)
            heatingPlan.xxNEWwithinRange(range) shouldBe false
        }

        test("within range returns false when high temperature exceeds the top bound") {
            val heatingPlan = HeatingPlan(Range(low = 70, high = 90))
            val range = Range(low = 65, high = 85)
            heatingPlan.xxNEWwithinRange(range) shouldBe false
        }

        test("within range returns false when temperature range is completely outside bounds") {
            val heatingPlan = HeatingPlan(Range(low = 90, high = 100))
            val range = Range(low = 65, high = 85)
            heatingPlan.xxNEWwithinRange(range) shouldBe false
        }
    })
