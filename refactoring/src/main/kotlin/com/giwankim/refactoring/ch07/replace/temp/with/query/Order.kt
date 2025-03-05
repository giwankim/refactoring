package com.giwankim.refactoring.ch07.replace.temp.with.query

class Order(
    val quantity: Int,
    val item: Item,
) {
    val price: Double
        get() {
            var discountFactor = 0.98
            if (basePrice > 1000) {
                discountFactor -= 0.03
            }
            return basePrice * discountFactor
        }

    val basePrice: Double
        get() = quantity * item.price
}
