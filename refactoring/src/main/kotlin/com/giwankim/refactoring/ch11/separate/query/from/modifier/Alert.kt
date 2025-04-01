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

fun setOffAlarms() {
    // set off alarms
}
