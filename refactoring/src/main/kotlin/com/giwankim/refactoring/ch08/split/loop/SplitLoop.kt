package com.giwankim.refactoring.ch08.split.loop

fun process(people: List<Person>): String {
    fun totalSalary(): Int = people.sumOf { it.salary }

    fun youngestAge(): Int = people.minOfOrNull { it.age } ?: throw IllegalStateException("people is empty")

    return "youngestAge: ${youngestAge()}, total salary: ${totalSalary()}"
}
