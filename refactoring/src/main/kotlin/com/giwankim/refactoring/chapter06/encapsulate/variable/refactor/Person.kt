package com.giwankim.refactoring.chapter06.encapsulate.variable.refactor

data class Person(
    val firstName: String,
    val lastName: String,
) {
    constructor(
        data: StringMap,
    ) : this(
        firstName = requireNotNull(data["firstName"]) { "first name is required" },
        lastName = requireNotNull(data["lastName"]) { "last name is required" },
    )
}
