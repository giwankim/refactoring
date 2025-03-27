package com.giwankim.refactoring.ch10.replace.control.flags.with.breaks

fun sendAlert() {
    // send alert
}

fun checkSecurity(people: List<Person>) {
    // some unimportant code
    var found = false
    for (person in people) {
        if (!found) {
            if (person.name == "Don") {
                sendAlert()
                found = true
            }
            if (person.name == "John") {
                sendAlert()
                found = true
            }
        }
    }
    // more code
}
