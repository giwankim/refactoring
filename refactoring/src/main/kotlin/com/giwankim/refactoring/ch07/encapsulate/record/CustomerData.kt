package com.giwankim.refactoring.ch07.encapsulate.record

data class CustomerDatum(
    val name: String,
    val id: Long,
    val usages: MutableMap<Int, MutableMap<Int, Int>>,
)

data class CustomerData(
    val data: MutableMap<Long, CustomerDatum>,
) {
    fun setUsage(
        customerID: Long,
        year: Int,
        month: Int,
        amount: Int,
    ) {
        data[customerID]!!.usages[year]!![month] = amount
    }
}

var customerData =
    CustomerData(
        mutableMapOf(
            1920L to
                CustomerDatum(
                    "martin",
                    1920L,
                    mutableMapOf(
                        2016 to mutableMapOf(1 to 50, 2 to 55),
                        2015 to mutableMapOf(1 to 70, 2 to 63),
                    ),
                ),
            38673L to CustomerDatum("neal", 38673L, mutableMapOf()),
        ),
    )

fun customerData(): CustomerData = customerData

fun getRawDataOfCustomers(): MutableMap<Long, CustomerDatum> = customerData.data

fun setRawDataOfCustomers(arg: MutableMap<Long, CustomerDatum>) {
    customerData = CustomerData(arg)
}

data class UsageComparisonResult(
    val laterAmount: Int,
    val change: Int,
)

fun compareUsage(
    customerID: Long,
    laterYear: Int,
    month: Int,
): UsageComparisonResult {
    val later = getRawDataOfCustomers()[customerID]!!.usages[laterYear]!!.getValue(month)
    val earlier = getRawDataOfCustomers()[customerID]!!.usages[laterYear - 1]!!.getValue(month)
    return UsageComparisonResult(laterAmount = later, change = later - earlier)
}
