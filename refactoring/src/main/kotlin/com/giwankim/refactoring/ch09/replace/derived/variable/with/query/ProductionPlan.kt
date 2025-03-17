package com.giwankim.refactoring.ch09.replace.derived.variable.with.query

class ProductionPlan {
    private var _production: Int = 0
    val production: Int
        get() {
            check(_production == calculatedProduction)
            return _production
        }
    val calculatedProduction: Int
        get() = adjustments.sumOf { it.amount }
    private val adjustments: MutableList<Adjustment> = mutableListOf()

    fun applyAdjustment(adjustment: Adjustment) {
        adjustments.add(adjustment)
        _production += adjustment.amount
    }
}
