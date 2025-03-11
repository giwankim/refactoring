package com.giwankim.refactoring.ch08.move.field

import java.time.LocalDateTime

class CustomerContract(
    val startDate: LocalDateTime,
    var discountRate: Double,
)
