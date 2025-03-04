package com.giwankim.refactoring.ch06.extract.variable

import kotlin.math.max
import kotlin.math.min

fun price(order: Order): Double {
    // price is base price - quantity discount + shipping
    return order.quantity * order.itemPrice -
        max(0, order.quantity - 500) * order.itemPrice * 0.05 +
        min(order.quantity * order.itemPrice * 0.1, 100.0)
}
