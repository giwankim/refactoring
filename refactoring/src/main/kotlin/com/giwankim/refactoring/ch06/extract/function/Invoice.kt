package com.giwankim.refactoring.ch06.extract.function

import java.time.LocalDateTime

class Invoice(
    val orders: List<Order>,
    var dueDate: LocalDateTime,
    val customer: String,
)
