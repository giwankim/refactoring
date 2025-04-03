package com.giwankim.refactoring.ch11.replace.error.code.with.exception

val countryData =
    CountryData(
        shippingRules =
            mapOf(
                "US" to ShippingRules(),
                "CA" to ShippingRules(),
                "JP" to ShippingRules(),
            ),
    )

class ShippingRules

data class CountryData(
    val shippingRules: Map<String, ShippingRules>,
)

fun localShippingRules(country: String): ShippingRules? = countryData.shippingRules[country]

fun calculateShippingCosts(order: Order): Int? {
    // irrelevant code
    val shippingRules = localShippingRules(order.country)
    if (shippingRules == null) {
        return null
    }
    // more irrelevant code
    return 0
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
