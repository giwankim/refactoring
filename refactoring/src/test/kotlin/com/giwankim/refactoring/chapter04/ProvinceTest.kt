package com.giwankim.refactoring.chapter04

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProvinceTest :
    FunSpec({
        context("provide") {
            test("shortfall") {
                val asia = Province.create(sampleProvinceData())
                asia.shortfall shouldBe 5
            }
        }
    })
