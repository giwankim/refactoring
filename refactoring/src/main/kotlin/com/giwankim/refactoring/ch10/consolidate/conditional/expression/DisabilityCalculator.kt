package com.giwankim.refactoring.ch10.consolidate.conditional.expression

fun disabilityAmount(anEmployee: Employee): Int {
    if (isNotEligibleForDisability(anEmployee)) {
        return 0
    }
    return 1
}

private fun isNotEligibleForDisability(anEmployee: Employee): Boolean =
    anEmployee.seniority < 2 || anEmployee.monthsDisabled > 12 || anEmployee.isPartTime
