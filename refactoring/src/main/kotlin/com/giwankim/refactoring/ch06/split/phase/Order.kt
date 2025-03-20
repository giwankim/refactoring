package com.giwankim.refactoring.ch06.split.phase

import java.math.BigDecimal

fun priceOrder(
    product: Product,
    quantity: Int,
    shippingMethod: ShippingMethod,
): BigDecimal {
    val priceData = calculatePricingData(product, quantity)
    val price = applyShipping(priceData, shippingMethod)
    return price
}

private fun calculatePricingData(
    product: Product,
    quantity: Int,
): PriceData {
    val basePrice = product.basePrice * quantity.toBigDecimal()
    val discount =
        (quantity - product.discountThreshold)
            .coerceAtLeast(0)
            .toBigDecimal() * product.basePrice * product.discountRate.toBigDecimal()
    val priceData = PriceData(basePrice = basePrice, quantity = quantity, discount = discount)
    return priceData
}

private fun applyShipping(
    priceData: PriceData,
    shippingMethod: ShippingMethod,
): BigDecimal {
    val shippingPerCase =
        if (priceData.basePrice > shippingMethod.discountThreshold) shippingMethod.discountedFee else shippingMethod.feePerCase
    val shippingCost = priceData.quantity.toBigDecimal() * shippingPerCase
    val price = priceData.basePrice - priceData.discount + shippingCost
    return price
}

data class PriceData(
    val basePrice: BigDecimal,
    val quantity: Int,
    val discount: BigDecimal,
)
