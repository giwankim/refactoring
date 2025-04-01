package com.giwankim.refactoring.ch11.parametrize.function

fun tenPercentRaise(person: Person) {
    raise(person, 0.1)
}

fun fivePercentRaise(person: Person) {
    raise(person, 0.05)
}

fun raise(
    person: Person,
    factor: Double,
) {
    person.salary *= 1 + factor
}
