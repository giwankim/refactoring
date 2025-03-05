package com.giwankim.refactoring.ch07.substitute.algorithm

fun foundPerson(people: List<String>): String {
    val candidates = listOf("Don", "John", "Kent")
    return people.find { it in candidates } ?: ""
}
