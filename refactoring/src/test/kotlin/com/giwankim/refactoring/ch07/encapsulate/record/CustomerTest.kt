package com.giwankim.refactoring.ch07.encapsulate.record

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CustomerTest :
    FunSpec({
        beforeTest {
            customerData =
                CustomerData(
                    mutableMapOf(
                        1920L to
                            CustomerDatum(
                                "martin",
                                1920L,
                                mutableMapOf(
                                    2016 to mutableMapOf(1 to 50, 2 to 55),
                                    2015 to mutableMapOf(1 to 70, 2 to 63),
                                ),
                            ),
                        38673L to CustomerDatum("neal", 38673L, mutableMapOf()),
                    ),
                )
        }

        test("update customer data") {
            customerData().setUsage(1920L, 2016, 1, 60)
            getRawDataOfCustomers()[1920L]?.usages[2016]?.get(1) shouldBe 60
        }

        test("compare usage") {
            compareUsage(1920L, 2016, 1) shouldBe UsageComparisonResult(laterAmount = 50, change = -20)
        }
    })
