package com.giwankim.refactoring.ch07.replace.primitive.with.objects

class Order(
    val priority: String,
)

fun List<Order>.highPriorityCount(): Int = this.count { it.priority == "high" || it.priority == "rush" }
