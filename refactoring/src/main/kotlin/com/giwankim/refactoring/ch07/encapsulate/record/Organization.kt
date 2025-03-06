package com.giwankim.refactoring.ch07.encapsulate.record

var organization = mutableMapOf("name" to "Acme Gooseberries", "country" to "GB")

fun getRawDataOfOrganization(): MutableMap<String, String> = organization
