package com.giwankim.refactoring.ch07.encapsulate.record

class Organization(
    var name: String,
    val country: String,
)

var organization = Organization(name = "Acme Gooseberries", country = "GB")

fun organization(): Organization = organization
