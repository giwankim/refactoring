package com.giwankim.refactoring.ch07.replace.primitive.with.objects

class Order(
    var _priority: Priority,
) {
    var priority: String
        get() = _priority.toString()
        set(value) {
            _priority = Priority(value)
        }
}

fun List<Order>.highPriorityCount(): Int = this.count { it.priority == "high" || it.priority == "rush" }
