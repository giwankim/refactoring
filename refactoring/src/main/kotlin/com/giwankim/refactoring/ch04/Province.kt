package com.giwankim.refactoring.ch04

class Province(
    var name: String,
    var demand: Int,
    var price: Int,
) {
    var totalProduction = 0
    private val _producers: MutableList<Producer> = mutableListOf()
    val producers: List<Producer>
        get() = _producers.toList()
    val shortfall: Int
        get() = demand - totalProduction
    val profit: Int
        get() = demandValue - demandCost
    val demandCost: Int
        get() {
            var remainingDemand = demand
            var result = 0
            _producers
                .sortedBy { it.cost }
                .forEach { producer ->
                    val contribution = minOf(remainingDemand, producer.production)
                    remainingDemand -= contribution
                    result += contribution * producer.cost
                }
            return result
        }
    val demandValue: Int
        get() = satisfiedDemand * price
    val satisfiedDemand: Int
        get() = minOf(demand, totalProduction)

    fun addProducer(producer: Producer) {
        _producers.add(producer)
        totalProduction += producer.production
    }

    companion object {
        fun create(document: ProvinceData): Province {
            val province = Province(document.name, document.demand, document.price)
            val producers = document.producers.map { Producer.create(province, it) }
            return province.apply { producers.forEach { addProducer(it) } }
        }
    }
}
