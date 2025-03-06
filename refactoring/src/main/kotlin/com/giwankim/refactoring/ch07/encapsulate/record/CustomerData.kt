package com.giwankim.refactoring.ch07.encapsulate.record

data class CustomerDatum(
    val name: String,
    val id: Long,
    val usages: MutableMap<Int, MutableMap<Int, Int>>,
)

data class CustomerData(
    val data: MutableMap<Long, CustomerDatum>,
) {
    val rawData: Map<Long, CustomerDatum>
        get() = data.toMap()

    fun setUsage(
        customerID: Long,
        year: Int,
        month: Int,
        amount: Int,
    ) {
        data[customerID]!!.usages[year]!![month] = amount
    }

    fun usage(
        customerID: Long,
        year: Int,
        month: Int,
    ): Int = data[customerID]!!.usages[year]!![month]!!
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

fun getRawDataOfCustomers(): Map<Long, CustomerDatum> = customerData.data.toMap()

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
    val later = customerData().usage(customerID, laterYear, month)
    val earlier = customerData().rawData[customerID]!!.usages[laterYear - 1]!![month]!!
    return UsageComparisonResult(laterAmount = later, change = later - earlier)
}
