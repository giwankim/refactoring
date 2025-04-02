package com.giwankim.refactoring.ch11.replace.parameter.with.query

class Order(
    val quantity: Int,
    val itemPrice: Double,
) {
    fun finalPrice(): Double {
        val basePrice = quantity * itemPrice
        return discountPrice(basePrice)
    }

    private fun discountLevel(): Int = if (quantity > 100) 2 else 1

    private fun discountPrice(basePrice: Double): Double =
        when (discountLevel()) {
            1 -> basePrice * 0.95
            2 -> basePrice * 0.9
            else -> throw IllegalArgumentException("Invalid discount level: ${discountLevel()}")
        }
}
