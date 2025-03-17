package com.giwankim.refactoring.ch09.replace.derived.variable.with.query

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProductionPlanTest :
    FunSpec({
        lateinit var sut: ProductionPlan

        beforeTest { sut = ProductionPlan() }

        test("production is zero") {
            sut.production shouldBe 0
        }

        test("applyAdjustment increases production") {
            sut.applyAdjustment(Adjustment(10))
            sut.production shouldBe 10
        }
    })
