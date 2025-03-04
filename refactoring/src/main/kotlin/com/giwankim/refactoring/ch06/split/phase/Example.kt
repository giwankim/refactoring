package com.giwankim.refactoring.ch06.split.phase

import java.math.BigDecimal

fun priceOrder(
    product: Product,
    quantity: Int,
    shippingMethod: ShippingMethod,
): BigDecimal {
    val basePrice = product.basePrice * quantity.toBigDecimal()
    val discount =
        (quantity - product.discountThreshold)
            .coerceAtLeast(0)
            .toBigDecimal() * product.basePrice * product.discountRate.toBigDecimal()
    val shippingPerCase =
        if (basePrice > shippingMethod.discountThreshold) shippingMethod.discountedFee else shippingMethod.feePerCase
    val shippingCost = quantity.toBigDecimal() * shippingPerCase
    val price = basePrice - discount + shippingCost
    return price
}
