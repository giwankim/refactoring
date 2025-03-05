package com.giwankim.refactoring.ch07.replace.primitive.with.objects

class Order(
    var priority: Priority,
) {
    val priorityString: String get() = priority.toString()
}

fun List<Order>.highPriorityCount(): Int = this.count { it.priority.higherThan(Priority("normal")) }
