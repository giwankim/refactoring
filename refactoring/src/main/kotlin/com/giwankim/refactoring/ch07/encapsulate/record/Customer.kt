package com.giwankim.refactoring.ch07.encapsulate.record

data class Customer(
    val name: String,
    val id: Long,
    val usages: MutableMap<Int, MutableMap<Int, Int>>,
)

var customerData: MutableMap<Long, Customer> =
    mutableMapOf(
        1920L to
            Customer(
                "martin",
                1920L,
                mutableMapOf(
                    2016 to mutableMapOf(1 to 50, 2 to 55),
                    2015 to mutableMapOf(1 to 70, 2 to 63),
                ),
            ),
        38673L to Customer("neal", 38673L, mutableMapOf()),
    )

data class UsageComparisonResult(
    val laterAmount: Int,
    val change: Int,
)

fun compareUsage(
    customerID: Long,
    laterYear: Int,
    month: Int,
): UsageComparisonResult {
    val later = customerData[customerID]!!.usages[laterYear]!!.getValue(month)
    val earlier = customerData[customerID]!!.usages[laterYear - 1]!!.getValue(month)
    return UsageComparisonResult(laterAmount = later, change = later - earlier)
}
