package com.giwankim.refactoring.ch07.substitute.algorithm

fun foundPerson(people: List<String>): String {
    for (i in people.indices) {
        if (people[i] == "Don") {
            return "Don"
        }
        if (people[i] == "John") {
            return "John"
        }
        if (people[i] == "Kent") {
            return "Kent"
        }
    }
    return ""
}
