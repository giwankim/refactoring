package com.giwankim.refactoring.ch07.encapsulate.record

class Organization(
    val data: MutableMap<String, String>,
) {
    var name: String
        get() = data.getValue("name")
        set(value) {
            data["name"] = value
        }
}

var organization = Organization(mutableMapOf("name" to "Acme Gooseberries", "country" to "GB"))

fun organization(): Organization = organization
