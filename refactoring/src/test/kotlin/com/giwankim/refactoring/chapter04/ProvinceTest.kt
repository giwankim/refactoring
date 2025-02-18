package com.giwankim.refactoring.chapter04

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProvinceTest :
    FunSpec({
        context("provide") {
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
        }
    })
