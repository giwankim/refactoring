package com.giwankim.refactoring.chapter06.split.phase.refactor

import java.math.BigDecimal

data class PriceData(
    val basePrice: BigDecimal,
    val quantity: Int,
    val discount: BigDecimal,
)
