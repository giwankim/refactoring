package com.giwankim.refactoring.ch07.inline.classes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ShipmentTest :
    FunSpec({
        test("tracking information") {
            val shipment = Shipment(shippingCompany = "moby dick", trackingNumber = "#white-whale")
            shipment.trackingInfo shouldBe "moby dick: #white-whale"
        }

        test("set shipping company") {
            val shipment = Shipment(shippingCompany = "moby dick", trackingNumber = "#white-whale")
            shipment.shippingCompany = "new vendor"
            shipment.shippingCompany shouldBe "new vendor"
        }

        test("set tracking number") {
            val shipment = Shipment(shippingCompany = "moby dick", trackingNumber = "#white-whale")
            shipment.trackingNumber = "#another-tracking-number"
            shipment.trackingNumber shouldBe "#another-tracking-number"
        }
    })
