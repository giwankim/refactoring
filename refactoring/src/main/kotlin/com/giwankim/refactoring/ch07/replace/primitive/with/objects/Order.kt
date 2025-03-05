package com.giwankim.refactoring.ch07.replace.primitive.with.objects

class Order(
    var _priority: Priority,
) {
    var priority: String
        get() = _priority.toString()
        set(value) {
            _priority = Priority(value)
        }
    val priorityString: String get() = _priority.toString()
}

fun List<Order>.highPriorityCount(): Int = this.count { it.priorityString == "high" || it.priorityString == "rush" }
