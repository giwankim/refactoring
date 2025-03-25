package com.giwankim.refactoring.ch10.consolidate.conditional.expression

fun disabilityAmount(anEmployee: Employee): Int {
    if (anEmployee.seniority < 2 || anEmployee.monthsDisabled > 12 || anEmployee.isPartTime) {
        return 0
    }
    return 1
}
