package com.giwankim.refactoring.ch10.decompose.conditional

import java.time.LocalDate

fun calculateCharge(
    plan: Plan,
    date: LocalDate,
    quantity: Int,
): Double {
    var charge: Double
    if (summer(date, plan)) {
        charge = quantity * plan.summerRate
    } else {
        charge = quantity * plan.regularRate + plan.regularServiceCharge
    }
    return charge
}

private fun summer(
    date: LocalDate,
    plan: Plan,
): Boolean = !date.isBefore(plan.summerStart) && !date.isAfter(plan.summerEnd)
