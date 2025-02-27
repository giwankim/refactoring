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
    val priceData = PriceData(basePrice)
    val price = applyShipping(priceData, shippingMethod, quantity, discount)
    return price
}

private fun applyShipping(
    priceData: PriceData,
    shippingMethod: ShippingMethod,
    quantity: Int,
    discount: BigDecimal,
): BigDecimal {
    val shippingPerCase =
        if (priceData.basePrice > shippingMethod.discountThreshold) shippingMethod.discountedFee else shippingMethod.feePerCase
    val shippingCost = quantity.toBigDecimal() * shippingPerCase
    val price = priceData.basePrice - discount + shippingCost
    return price
}

data class PriceData(
    val basePrice: BigDecimal,
)
