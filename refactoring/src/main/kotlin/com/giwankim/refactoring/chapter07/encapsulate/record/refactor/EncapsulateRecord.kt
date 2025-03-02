package com.giwankim.refactoring.chapter07.encapsulate.record.refactor

val organization = mutableMapOf("name" to "Acme Gooseberries", "country" to "GB")

fun getRawDataOfOrganization(): MutableMap<String, String> = organization
