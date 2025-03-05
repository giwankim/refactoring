package com.giwankim.refactoring.ch07.remove.middle.man

class Person(
    val name: String,
    val department: Department,
) {
    val manager: Person?
        get() = department.manager
}
