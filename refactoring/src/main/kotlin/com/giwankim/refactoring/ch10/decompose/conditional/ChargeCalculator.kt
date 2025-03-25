package com.giwankim.refactoring.ch10.decompose.conditional

import java.time.LocalDate

fun calculateCharge(
    plan: Plan,
    date: LocalDate,
    quantity: Int,
): Double = if (summer(date, plan)) summerCharge(quantity, plan) else regularCharge(quantity, plan)

private fun summer(
    date: LocalDate,
    plan: Plan,
): Boolean = !date.isBefore(plan.summerStart) && !date.isAfter(plan.summerEnd)

private fun summerCharge(
    quantity: Int,
    plan: Plan,
): Double = quantity * plan.summerRate

private fun regularCharge(
    quantity: Int,
    plan: Plan,
): Double = quantity * plan.regularRate + plan.regularServiceCharge
