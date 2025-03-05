package com.giwankim.refactoring.ch07.replace.primitive.with.objects

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OrderTest :
    FunSpec({
        test("high priority count") {
            val orders = listOf(Order(Priority("high")), Order(Priority("low")), Order(Priority("rush")))
            orders.highPriorityCount() shouldBe 2
        }
    })
