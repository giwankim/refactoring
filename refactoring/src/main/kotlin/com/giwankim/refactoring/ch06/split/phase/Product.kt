package com.giwankim.refactoring.ch06.split.phase

import java.math.BigDecimal

class Product(
    val basePrice: BigDecimal,
    val discountRate: Double,
    val discountThreshold: Int,
)
