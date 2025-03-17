package com.giwankim.refactoring.ch09.replace.derived.variable.with.query

class ProductionPlan {
    private var _production: Int = 0
    val production: Int
        get() = adjustments.sumOf<Adjustment> { it.amount }
    private val adjustments: MutableList<Adjustment> = mutableListOf()

    fun applyAdjustment(adjustment: Adjustment) {
        adjustments.add(adjustment)
        _production += adjustment.amount
    }
}
