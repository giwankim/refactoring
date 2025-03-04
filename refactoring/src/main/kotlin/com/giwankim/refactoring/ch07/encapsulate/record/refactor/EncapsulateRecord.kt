package com.giwankim.refactoring.ch07.encapsulate.record.refactor

val organization = Organization(mutableMapOf("name" to "Acme Gooseberries", "country" to "GB"))

fun organization(): Organization = organization

class Organization(
    var name: String,
    var country: String,
) {
    constructor(data: MutableMap<String, String>) : this(data.getValue("name"), data.getValue("country"))
}
