package com.giwankim.refactoring.ch07.inline.classes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ShipmentTest :
    FunSpec({
        test("tracking information") {
            val shipment = Shipment(TrackingInformation("moby dick", "#white-whale"))
            shipment.trackingInfo shouldBe "moby dick: #white-whale"
        }

        test("set shipping company") {
            val shipment = Shipment(TrackingInformation("moby dick", "#white-whale"))
            shipment.shippingCompany = "new vendor"
            shipment.shippingCompany shouldBe "new vendor"
        }
    })
