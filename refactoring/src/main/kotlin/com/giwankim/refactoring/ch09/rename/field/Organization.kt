package com.giwankim.refactoring.ch09.rename.field

data class Organization(
    var title: String,
    var country: String,
) {
    constructor(data: Map<String, String>) : this(
        title = data["title"] ?: data["name"] ?: "",
        country = data["country"] ?: "",
    )
}
