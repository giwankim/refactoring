package com.giwankim.refactoring.ch11.remove.flag.argument

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class DeliveryTest :
    FunSpec({
        val baseDate = LocalDateTime.of(2023, 3, 15, 10, 0)

        context("Rush delivery") {
            test("MA state should deliver in 2 days") {
                val order = Order(placedOn = baseDate, deliveryState = "MA")
                deliveryDate(order, true) shouldBe baseDate.plusDays(2)
            }

            test("CT state should deliver in 2 days") {
                val order = Order(placedOn = baseDate, deliveryState = "CT")
                deliveryDate(order, true) shouldBe baseDate.plusDays(2)
            }

            test("NY state should deliver in 3 days") {
                val order = Order(placedOn = baseDate, deliveryState = "NY")
                deliveryDate(order, true) shouldBe baseDate.plusDays(3)
            }

            test("NJ state should deliver in 3 days") {
                val order = Order(placedOn = baseDate, deliveryState = "NJ")
                deliveryDate(order, true) shouldBe baseDate.plusDays(3)
            }

            test("other states should deliver in 4 days") {
                val order = Order(placedOn = baseDate, deliveryState = "CA")
                deliveryDate(order, true) shouldBe baseDate.plusDays(4)
            }
        }

        context("Regular delivery") {
            test("MA state should deliver in 4 days") {
                val order = Order(placedOn = baseDate, deliveryState = "MA")
                deliveryDate(order, false) shouldBe baseDate.plusDays(4)
            }

            test("CT state should deliver in 4 days") {
                val order = Order(placedOn = baseDate, deliveryState = "CT")
                deliveryDate(order, false) shouldBe baseDate.plusDays(4)
            }

            test("NY state should deliver in 4 days") {
                val order = Order(placedOn = baseDate, deliveryState = "NY")
                deliveryDate(order, false) shouldBe baseDate.plusDays(4)
            }

            test("ME state should deliver in 5 days") {
                val order = Order(placedOn = baseDate, deliveryState = "ME")
                deliveryDate(order, false) shouldBe baseDate.plusDays(5)
            }

            test("NH state should deliver in 5 days") {
                val order = Order(placedOn = baseDate, deliveryState = "NH")
                deliveryDate(order, false) shouldBe baseDate.plusDays(5)
            }

            test("other states should deliver in 6 days") {
                val order = Order(placedOn = baseDate, deliveryState = "CA")
                deliveryDate(order, false) shouldBe baseDate.plusDays(6)
            }
        }
    })
