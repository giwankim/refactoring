package com.giwankim.refactoring.ch06.split.phase.refactor

import com.giwankim.refactoring.ch06.split.phase.Product
import com.giwankim.refactoring.ch06.split.phase.ShippingMethod
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class SplitPhaseTest :
    FunSpec({
        test("priceOrder") {
            val product = Product(basePrice = 10.toBigDecimal(), discountRate = 0.1, discountThreshold = 10)
            val quantity = 10
            val shippingMethod =
                ShippingMethod(discountThreshold = 100.toBigDecimal(), discountedFee = 5.toBigDecimal(), feePerCase = 10.toBigDecimal())

            val price = priceOrder(product, quantity, shippingMethod)

            price.toDouble() shouldBe (200.0 plusOrMinus 1e-6)
        }
    })
