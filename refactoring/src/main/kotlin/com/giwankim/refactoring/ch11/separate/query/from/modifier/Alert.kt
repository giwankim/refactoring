package com.giwankim.refactoring.ch11.separate.query.from.modifier

fun alertForMiscreant(people: List<Person>) {
    if (findMiscreant(people) != null) {
        setOffAlarms()
    }
}

fun findMiscreant(people: List<Person>): Person? {
    for (p in people) {
        if (p.name == "Don") {
            return p
        }
        if (p.name == "John") {
            return p
        }
    }
    return null
}

fun setOffAlarms() {
    // set off alarms
}
