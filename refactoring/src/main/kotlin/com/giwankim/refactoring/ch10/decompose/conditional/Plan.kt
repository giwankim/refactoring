package com.giwankim.refactoring.ch10.decompose.conditional

import java.time.LocalDate

data class Plan(
    val summerStart: LocalDate,
    val summerEnd: LocalDate,
    val summerRate: Double,
    val regularRate: Double,
    val regularServiceCharge: Double,
)
