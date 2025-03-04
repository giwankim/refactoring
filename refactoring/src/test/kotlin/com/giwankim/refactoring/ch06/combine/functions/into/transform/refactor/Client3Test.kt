package com.giwankim.refactoring.ch06.combine.functions.into.transform.refactor

import com.giwankim.refactoring.ch06.combine.functions.into.transform.Reading
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.Month
import java.time.Year

class Client3Test :
    FunSpec({
        test("check reading unchanged") {
            val baseReading = Reading("ivan", 15, Month.of(5), Year.of(2017))
            val oracle = baseReading.copy()
            enrichReading(baseReading)
            baseReading shouldBe oracle
        }
    })
