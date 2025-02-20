package com.giwankim.refactoring.chapter04

class Producer(
    val province: Province,
    val name: String,
    var cost: Int,
    initialProduction: Int = 0,
) {
    private var _production: Int = initialProduction
    var production: Int
        get() = _production
        set(value) {
            province.totalProduction += value - _production
            _production = value
        }

    companion object {
        fun create(
            province: Province,
            data: ProducerData,
        ): Producer = Producer(province, data.name, data.cost, data.production)
    }
}
