package com.giwankim.refactoring.ch07.encapsulate.record

class Organization(
    val data: MutableMap<String, String>,
)

var organization = Organization(mutableMapOf("name" to "Acme Gooseberries", "country" to "GB"))

fun getRawDataOfOrganization(): MutableMap<String, String> = organization.data

fun organization(): Organization = organization
