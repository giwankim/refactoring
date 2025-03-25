package com.giwankim.refactoring.ch10.consolidate.conditional.expression

fun disabilityAmount(anEmployee: Employee): Int {
    if (isNotEligableForDisability(anEmployee)) {
        return 0
    }
    return 1
}

private fun isNotEligableForDisability(anEmployee: Employee): Boolean =
    anEmployee.seniority < 2 || anEmployee.monthsDisabled > 12 || anEmployee.isPartTime
