package com.giwankim.refactoring.chapter07.encapsulate.record

val organization = mutableMapOf("name" to "Acme Gooseberries", "country" to "GB")

fun main() {
    var result = ""
    result += "<h1>${organization["name"]}</h1>"
    organization["name"] = "New Acme Gooseberries"
}
