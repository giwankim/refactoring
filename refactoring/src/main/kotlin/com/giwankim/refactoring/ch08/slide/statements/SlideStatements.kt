package com.giwankim.refactoring.ch08.slide.statements

import kotlin.math.max

class Order(
    val units: Int,
    val isRepeat: Boolean,
)

class PricingPlan(
    val base: Double,
    val unit: Double,
    val discountThreshold: Int,
    val discountFactor: Double,
)

fun main() {
    val pricingPlan = retrievePricingPlan()
    val baseCharge = pricingPlan.base
    var charge: Double
    val chargePerUnit = pricingPlan.unit
    val order = retrieveOrder()
    val units = order.units
    charge = baseCharge + units * chargePerUnit
    var discountableUnits = max(units - pricingPlan.discountThreshold, 0)
    var discount = discountableUnits * pricingPlan.discountFactor
    if (order.isRepeat) {
        discount += 20
    }
    charge = charge - discount
    chargeOrder(charge)
}

fun retrievePricingPlan() = PricingPlan(base = 10.0, unit = 2.0, discountThreshold = 5, discountFactor = 0.1)

fun retrieveOrder() = Order(units = 1, isRepeat = false)

fun chargeOrder(charge: Double) {
    println("Charge: $charge")
}
