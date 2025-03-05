package com.giwankim.refactoring.ch07.inline.classes

class Shipment(
    var trackingInformation: TrackingInformation,
) {
    val trackingInfo: String
        get() = trackingInformation.display()
}
