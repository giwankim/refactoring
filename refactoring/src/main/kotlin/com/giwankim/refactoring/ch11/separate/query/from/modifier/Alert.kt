package com.giwankim.refactoring.ch11.separate.query.from.modifier

fun alertForMiscreant(people: List<Person>): Person? {
    for (p in people) {
        if (p.name == "Don") {
            setOffAlarms()
            return p
        }
        if (p.name == "John") {
            setOffAlarms()
            return p
        }
    }
    return null
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
