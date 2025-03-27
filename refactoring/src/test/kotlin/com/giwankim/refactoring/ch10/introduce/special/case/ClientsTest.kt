package com.giwankim.refactoring.ch10.introduce.special.case

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ClientsTest :
    FunSpec({
        context("customer is null for site") {
            val site = Site()

            test("client1 returns 'occupant' when customer is unknown") {
                client1(site) shouldBe "occupant"
            }

            test("client2 returns BASIC billing plan when customer is unknown") {
                client2(site) shouldBe BillingPlan.BASIC
            }

            test("client3 does not modify the billing plan when customer is unknown") {
                client3(site, BillingPlan.DELUXE)
                site.customer.billingPlan shouldBe BillingPlan.BASIC
            }

            test("client4 returns 0 as default weeks delinquent last year when customer is unknown") {
                client4(site) shouldBe 0
            }
        }

        context("customer exists for site") {
            val customer = Customer("John Doe", BillingPlan.DELUXE, PaymentHistory(3))
            val site = Site(customer)

            test("client1 returns customer name when customer exists") {
                client1(site) shouldBe "John Doe"
            }

            test("client2 returns customer's billing plan when customer exists") {
                client2(site) shouldBe BillingPlan.DELUXE
            }

            test("client3 updates billing plan when customer exists") {
                client3(site, BillingPlan.SPECIAL)
                customer.billingPlan shouldBe BillingPlan.SPECIAL
            }

            test("client4 returns weeks delinquent when customer exists") {
                client4(site) shouldBe 3
            }
        }
    })
