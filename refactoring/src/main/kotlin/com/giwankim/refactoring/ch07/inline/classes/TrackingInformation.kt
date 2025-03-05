package com.giwankim.refactoring.ch07.inline.classes

class TrackingInformation(
    var shippingCompany: String,
    var trackingNumber: String,
) {
    fun display(): String = "$shippingCompany: $trackingNumber"
}
