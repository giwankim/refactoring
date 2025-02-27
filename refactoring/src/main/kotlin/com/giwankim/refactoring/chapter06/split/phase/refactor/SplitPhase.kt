package com.giwankim.refactoring.chapter06.split.phase.refactor

import com.giwankim.refactoring.chapter06.split.phase.Product
import com.giwankim.refactoring.chapter06.split.phase.ShippingMethod
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
    val price = applyShipping(basePrice, shippingMethod, quantity, discount)
    return price
}

private fun applyShipping(
    basePrice: BigDecimal,
    shippingMethod: ShippingMethod,
    quantity: Int,
    discount: BigDecimal,
): BigDecimal {
    val shippingPerCase =
        if (basePrice > shippingMethod.discountThreshold) shippingMethod.discountedFee else shippingMethod.feePerCase
    val shippingCost = quantity.toBigDecimal() * shippingPerCase
    val price = basePrice - discount + shippingCost
    return price
}
