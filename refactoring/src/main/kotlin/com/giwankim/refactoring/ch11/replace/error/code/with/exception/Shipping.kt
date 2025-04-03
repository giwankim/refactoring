package com.giwankim.refactoring.ch11.replace.error.code.with.exception

val countryData =
    CountryData(
        shippingRules =
            mapOf(
                "US" to ShippingRules(1),
                "CA" to ShippingRules(2),
                "JP" to ShippingRules(3),
            ),
    )

class ShippingRules(
    val cost: Int,
)

data class CountryData(
    val shippingRules: Map<String, ShippingRules>,
)

fun localShippingRules(country: String): ShippingRules = countryData.shippingRules[country] ?: throw OrderProcessingError(-23)

fun calculateShippingCosts(order: Order): Int? {
    // irrelevant code
    val shippingRules = localShippingRules(order.country)
    // more irrelevant code
    return shippingRules.cost
}

fun main() {
    val errorList = mutableListOf<ErrorData>()
    val orderData = Order("KR")

    var status: Int? = null
    try {
        status = calculateShippingCosts(orderData)
    } catch (ope: OrderProcessingError) {
        errorList.add(ErrorData(order = orderData, errorCode = ope.errorCode))
    } catch (e: Exception) {
        throw e
    }
    if (status == null) {
        errorList.add(ErrorData(orderData, null))
    }
}
