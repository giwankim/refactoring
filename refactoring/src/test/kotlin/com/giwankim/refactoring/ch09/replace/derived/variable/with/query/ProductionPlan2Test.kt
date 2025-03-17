package com.giwankim.refactoring.ch09.replace.derived.variable.with.query

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProductionPlan2Test :
    FunSpec({
        lateinit var sut: ProductionPlan2

        test("production is zero") {
            sut = ProductionPlan2(10)
            sut.production shouldBe 10
        }

        test("applyAdjustment increases production") {
            sut = ProductionPlan2(10)
            sut.applyAdjustment(Adjustment(20))
            sut.production shouldBe 30
        }
    })
