package com.giwankim.refactoring.ch09.replace.derived.variable.with.query

class ProductionPlan2(
    production: Int,
) {
    private var initialProduction: Int = production
    private var productionAccumulator: Int = 0
    private val adjustments: MutableList<Adjustment> = mutableListOf()
    val production: Int
        get() {
            check(productionAccumulator == calculateProductionAccumulator)
            return initialProduction + productionAccumulator
        }
    val calculateProductionAccumulator: Int
        get() = adjustments.sumOf { it.amount }

    fun applyAdjustment(adjustment: Adjustment) {
        adjustments.add(adjustment)
        productionAccumulator += adjustment.amount
    }
}
