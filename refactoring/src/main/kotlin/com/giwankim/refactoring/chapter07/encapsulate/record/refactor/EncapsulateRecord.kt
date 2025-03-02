package com.giwankim.refactoring.chapter07.encapsulate.record.refactor

val organization = Organization(mutableMapOf("name" to "Acme Gooseberries", "country" to "GB"))

fun organization(): Organization = organization

class Organization(
    val data: MutableMap<String, String>,
) {
    var name: String
        get() = data["name"] ?: ""
        set(value) {
            data["name"] = value
        }
}
