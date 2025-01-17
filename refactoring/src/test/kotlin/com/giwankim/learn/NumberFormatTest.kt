package com.giwankim.learn

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.text.NumberFormat
import java.util.Locale

class NumberFormatTest :
    FunSpec({
        val format = NumberFormat.getCurrencyInstance(Locale.US)

        test("앞에 '$'가 붙는다") {
            format.format(1000).first() shouldBe '$'
        }

        test("소수점이 없는 경우에도 두 자리가 나온다") {
            format.format(1000).endsWith(".00") shouldBe true
        }

        test("소수점이 있는 경우에 두 자리로 나온다") {
            format.format(1000.1).endsWith(".10") shouldBe true
        }

        test("소수점을 두 자리까지 반올림한다") {
            format.format(1000.111).endsWith(".11") shouldBe true
            format.format(1000.115).endsWith(".12") shouldBe true
            format.format(1000.116).endsWith(".12") shouldBe true
        }

        test("세 자리마다 콤마가 찍힌다") {
            format.format(1000000) shouldBe "$1,000,000.00"
        }
    })
