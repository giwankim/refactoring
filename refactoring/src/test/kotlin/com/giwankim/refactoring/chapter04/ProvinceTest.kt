package com.giwankim.refactoring.chapter04

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProvinceTest :
    FunSpec({
        context("province") {
            lateinit var asia: Province

            beforeEach {
                asia = Province.create(sampleProvinceData())
            }

            test("shortfall") {
                asia.shortfall shouldBe 5
            }

            test("profit") {
                asia.profit shouldBe 230
            }

            test("change production") {
                asia.producers[0].production = 20
                asia.shortfall shouldBe -6
                asia.profit shouldBe 292
            }

            test("zero demand") {
                asia.demand = 0
                asia.shortfall shouldBe -25
                asia.profit shouldBe 0
            }

            test("negative demand") {
                asia.demand = -1
                asia.shortfall shouldBe -26
                asia.profit shouldBe -10
            }
        }

        context("no producers") {
            lateinit var noProducers: Province

            beforeEach {
                val data = ProvinceData(name = "No producers", producers = emptyList(), demand = 30, price = 20)
                noProducers = Province.create(data)
            }

            test("shortfall") {
                noProducers.shortfall shouldBe 30
            }

            test("profit") {
                noProducers.profit shouldBe 0
            }
        }
    })
