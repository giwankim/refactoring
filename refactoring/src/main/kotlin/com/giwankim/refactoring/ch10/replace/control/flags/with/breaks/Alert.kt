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
    if (listOf("Don", "John").isDisjointWith(people)) {
        return
    }
    sendAlert()
}

private fun List<String>.isDisjointWith(people: List<Person>): Boolean = intersect(people.map { it.name }).isEmpty()
