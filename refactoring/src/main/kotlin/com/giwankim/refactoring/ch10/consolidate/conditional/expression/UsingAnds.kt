package com.giwankim.refactoring.ch10.consolidate.conditional.expression

class Employee2(
    val onVacation: Boolean,
    val seniority: Int,
)

fun before(employee: Employee2): Double {
    if (employee.onVacation) {
        if (employee.seniority > 10) {
            return 1.0
        }
    }
    return 0.5
}

fun after(employee: Employee2): Double {
    if (employee.onVacation && employee.seniority > 10) {
        return 1.0
    }
    return 0.5
}
