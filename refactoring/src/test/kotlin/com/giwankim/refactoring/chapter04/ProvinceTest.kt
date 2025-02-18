package com.giwankim.refactoring.chapter04

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProvinceTest :
    FunSpec({
        lateinit var asia: Province

        beforeEach {
            asia = Province.create(sampleProvinceData())
        }

        context("provide") {

            test("shortfall") {
                asia.shortfall shouldBe 5
            }

            test("profit") {
                asia.profit shouldBe 230
            }
        }

        context("province") {
            test("change production") {
                asia.producers[0].production = 20
                assertSoftly {
                    asia.shortfall shouldBe -6
                    asia.profit shouldBe 292
                }
            }
        }
    })
