package com.giwankim.refactoring.ch06.split.phase

import java.math.BigDecimal

class ShippingMethod(
    val discountThreshold: BigDecimal,
    val discountedFee: BigDecimal,
    val feePerCase: BigDecimal,
)
