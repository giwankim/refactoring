package com.giwankim.refactoring.ch11.replace.parameter.with.query

class Order(
    val quantity: Int,
    val itemPrice: Double,
) {
    fun finalPrice(): Double {
        val basePrice = quantity * itemPrice
        val discountLevel = if (quantity > 100) 2 else 1
        return discountPrice(basePrice, discountLevel)
    }

    fun discountPrice(
        basePrice: Double,
        discountLevel: Int,
    ): Double {
        when (discountLevel) {
            1 -> return basePrice * 0.95
            2 -> return basePrice * 0.9
            else -> throw IllegalArgumentException("Invalid discount level: $discountLevel")
        }
    }
}
