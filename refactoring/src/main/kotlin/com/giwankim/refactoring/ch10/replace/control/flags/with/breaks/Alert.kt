package com.giwankim.refactoring.ch10.replace.control.flags.with.breaks

fun sendAlert() {
    // send alert
}

fun checkSecurity(people: List<Person>) {
    // some unimportant code
    checkForMiscreants(people)
    // more code
}

private fun checkForMiscreants(people: List<Person>) {
    for (person in people) {
        if (person.name == "Don") {
            sendAlert()
            return
        }
        if (person.name == "John") {
            sendAlert()
            return
        }
    }
}
