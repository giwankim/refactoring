package com.giwankim.refactoring.ch09.replace.derived.variable.with.query

class ProductionPlan2(
    production: Int,
) {
    private var initialProduction: Int = production
    private val adjustments: MutableList<Adjustment> = mutableListOf()
    val production: Int
        get() = initialProduction + adjustments.sumOf { it.amount }

    fun applyAdjustment(adjustment: Adjustment) {
        adjustments.add(adjustment)
    }
}
