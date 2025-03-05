package com.giwankim.refactoring.ch07.inline.classes

class Shipment(
    var shippingCompany: String,
    var trackingNumber: String,
) {
    val trackingInfo: String
        get() = "$shippingCompany: $trackingNumber"
}
