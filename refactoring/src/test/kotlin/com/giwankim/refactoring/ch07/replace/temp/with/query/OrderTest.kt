package com.giwankim.refactoring.ch07.replace.temp.with.query

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class OrderTest :
    FunSpec({
        test("price") {
            val order = Order(10, Item(100.0))
            order.price shouldBe 980.0 plusOrMinus 1e-3
        }

        test("price with discount") {
            val order = Order(11, Item(100.0))
            order.price shouldBe 1045.0 plusOrMinus 1e-3
        }
    })
