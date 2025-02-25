package com.giwankim.refactoring.chapter06.extract.function

import java.time.LocalDateTime

class Invoice(
    val orders: List<Order>,
    var dueDate: LocalDateTime,
    val customer: String,
)
