package com.giwankim.refactoring.ch06.split.phase.refactor

import com.giwankim.refactoring.ch06.split.phase.Product
import com.giwankim.refactoring.ch06.split.phase.ShippingMethod
import java.math.BigDecimal

fun priceOrder(
    product: Product,
    quantity: Int,
    shippingMethod: ShippingMethod,
): BigDecimal {
    val priceData = calculatePricingData(product, quantity)
    return applyShipping(priceData, shippingMethod)
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
    return PriceData(basePrice = basePrice, quantity = quantity, discount = discount)
}

private fun applyShipping(
    priceData: PriceData,
    shippingMethod: ShippingMethod,
): BigDecimal {
    val shippingPerCase =
        if (priceData.basePrice > shippingMethod.discountThreshold) shippingMethod.discountedFee else shippingMethod.feePerCase
    val shippingCost = priceData.quantity.toBigDecimal() * shippingPerCase
    return priceData.basePrice - priceData.discount + shippingCost
}
