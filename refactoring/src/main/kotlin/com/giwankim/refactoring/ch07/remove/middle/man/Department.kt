package com.giwankim.refactoring.ch07.remove.middle.man

class Department(
    var manager: Person? = null,
) {
    fun assignManager(person: Person) {
        manager = person
    }
}
