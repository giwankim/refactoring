package com.giwankim.refactoring.ch11.remove.flag.argument

import java.time.LocalDateTime

class Order(
    val placedOn: LocalDateTime,
    val deliveryState: String,
)
