package com.giwankim.refactoring.ch09.rename.field

data class Organization(
    var name: String,
    var country: String,
) {
    constructor(data: Map<String, String>) : this(
        name = data["name"] ?: "",
        country = data["country"] ?: "",
    )
}
