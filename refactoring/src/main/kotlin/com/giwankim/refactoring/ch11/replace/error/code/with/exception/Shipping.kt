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

fun calculateShippingCosts(order: Order): Double? {
    // irrelevant code
    val shippingRules = localShippingRules(order.country)
    if (shippingRules == null) {
        return shippingRules
    }
    // more irrevelant code
    return 0.0
}
